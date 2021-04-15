package com.qunjie.jindie.invoice.service;/**
 * Created by whs on 2020/12/10.
 */

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.service.EmailServiceHelper;
import com.qunjie.common.response.ApiResult;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.jindie.config.K3CloudConfig;
import com.qunjie.jindie.invoice.constants.FieldNameMain;
import com.qunjie.jindie.invoice.model.InvoiceEntity;
import com.qunjie.jindie.invoice.vo.FBillHead;
import com.qunjie.jindie.invoice.vo.FSaleSicentry;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import com.qunjie.jindie.saleorder.view.service.SaleOrderViewService;
import com.qunjie.jindie.util.JindieHelperUtil;
import kingdee.bos.webapi.client.K3CloudApiClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.manager.SaveService
 *
 * @author whs
 *         Date:   2020/12/10  18:11
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Service
public class InvoiceSaveService {

    @Autowired
    EmailServiceHelper emailServiceHelper;
    @Autowired
    SaleOrderViewService saleOrderViewService;

    private static Logger log = LoggerFactory.getLogger(InvoiceSaveService.class);

    static final K3CloudConfig k3CloudConfig = SpringBeanUtils.getBean(K3CloudConfig.class);
    static String K3CloudURL = k3CloudConfig.getUrl();
    static String dbId = k3CloudConfig.getDbId();
    static String uid = k3CloudConfig.getUid();
    static String pwd = k3CloudConfig.getPwd();
    static int lang = 2052;
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static String IV_SALESIC = "IV_SALESIC";
    static String IV_SALESOC = "IV_SALESOC";


    public int save(WorkflowRequestTable workflowRequestTable) throws Exception {

        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            FBillHead model = new FBillHead();
            Map map = model.valueOf(workflowRequestTable.getMains());
            String sFormId;
            String htbh = (String) map.get(FieldNameMain.HTBH.name());
            String fplx = (String) map.get(FieldNameMain.FPLX.name());
            if ("1".equals(fplx)){//1--普票,0--专票
                sFormId = IV_SALESOC;
            }else {
                sFormId = IV_SALESIC;
            }

            JSONObject jsonObject = saleOrderViewService.view(client,htbh);
            /**
             * 客户选取销售订单中的客户，不用开票里填的客户
             */
            try {
                String custnm = jsonObject.getJSONObject("Result").getJSONObject("Result").getJSONObject("CustId").getString("Number");
                if (!StringUtils.isEmpty(custnm)) {
                    model.setFCUSTOMERID(new Entity2(custnm));
                }
            }catch (Exception e){
                log.info("================="+htbh+"=================销售订单关联关系为空=================================");
            }

            /**
             * 关联关系如果为空，不传给金蝶，且返回失败，流程不往下流转
             */
            AtomicBoolean flag = new AtomicBoolean(false);
            List<FSaleSicentry> fSaleSicentries = new ArrayList<>();
            if (!CollectionUtils.isEmpty(workflowRequestTable.getDetails())){
                workflowRequestTable.getDetails().forEach(e->{
                    FSaleSicentry fSaleSicentry = new FSaleSicentry();
                    fSaleSicentry.valueOf(e,htbh,jsonObject);
                    fSaleSicentries.add(fSaleSicentry);
                    if (CollectionUtils.isEmpty(fSaleSicentry.getFSALESICENTRY_Link())){
                        flag.set(true);
                    }
                });
               model.setFSALESICENTRY(fSaleSicentries);
            }
            if (flag.get()){
                log.info("==================================销售订单关联关系为空 || 明细中的物料在金蝶中不存在==============不做推送==================fault==============");
                emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTOAll,"开票推送金蝶失败","时间："+sdf.format(new Date())+"\n泛微中销售订单号为:"+htbh+"\n在金蝶中不存在此销售订单或者销售订单明细不匹配，请查看!!");
                return 0;
            }
            InvoiceEntity invoiceEntity = new InvoiceEntity(model);
            String s = new Gson().toJson(invoiceEntity);
            log.debug("==================入参========================"+s);
            String sResult = client.save(sFormId, s);
            log.debug("Save success results:"+sResult);
            ApiResult apiResult = ApiResult.isSuccess(sResult);
            ApiResult apiResult1 = JindieHelperUtil.commitAndAudit(apiResult, client, sFormId);
            if (apiResult1 != null && apiResult.getCode() == 1){
                return 1;
            }
        }
        return 0;
    }

}

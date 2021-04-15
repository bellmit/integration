package com.qunjie.jindie.saleorder.save.service;/**
 * Created by whs on 2020/12/10.
 */

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.axis.model.WorkflowId;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.common.consts.SaveSaleOrderMessage;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.service.EmailServiceHelper;
import com.qunjie.common.response.ApiResult;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.jindie.config.K3CloudConfig;
import com.qunjie.jindie.saleorder.audit.model.AuditEntity;
import com.qunjie.jindie.saleorder.save.enums.FieldName;
import com.qunjie.jindie.saleorder.save.model.SaleOrderEntity;
import com.qunjie.jindie.saleorder.save.vo.FBillHead;
import com.qunjie.jindie.saleorder.submit.model.SubmitEntity;
import com.qunjie.jindie.saleorder.view.model.ViewEntity;
import kingdee.bos.webapi.client.K3CloudApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

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
@Slf4j
public class SaveService {

    @Autowired
    private EmailServiceHelper emailServiceHelper;

    static final K3CloudConfig k3CloudConfig = SpringBeanUtils.getBean(K3CloudConfig.class);
    static String K3CloudURL = k3CloudConfig.getUrl();
    static String dbId = k3CloudConfig.getDbId();
    static String uid = k3CloudConfig.getUid();
    static String pwd = k3CloudConfig.getPwd();
    static int lang = 2052;

    static String sFormId = "SAL_SaleOrder";


    public ApiResult save(WorkflowRequestTable workflowRequestTable,Integer workflowid) throws Exception {

        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            FBillHead model = new FBillHead();
            Map<String, String> map = null;
            if (WorkflowId.QDXSDD.contains(workflowid)){
                map = model.QDXSDDvalueOf(workflowRequestTable);
            }else {
                map = model.valueOf(workflowRequestTable);
            }
            /**
             * 如果是框架协议为"是"，则不传金蝶，返回成功
             */
            if (map.get(FieldName.SFKJXY.name()) != null && "0".equals(map.get(FieldName.SFKJXY.name()))){
                log.info("======================框架协议为\"是\"，不传金蝶=====================");
                return new ApiResult(1, SaveSaleOrderMessage.SUCCESS,null);
            }
            /**
             * 明细中数量为0的明细不传金蝶，
             * 如果明细都为0，整个销售订单都不传,并且金蝶推送认为是成功
             */
            if (CollectionUtils.isEmpty(model.getFSaleOrderEntry())){
                log.info("销售订单明细为空，或者明细的数量为空，无明细详情，该销售订单不传金蝶");
                return new ApiResult(1, SaveSaleOrderMessage.SUCCESS,null);
            }

            SaleOrderEntity saleOrderEntity = new SaleOrderEntity(model);

            String s = new Gson().toJson(saleOrderEntity);

            log.debug("==================入参========================"+s);
            String sResult = client.save(sFormId,s);
            log.debug("Save success results:"+sResult);
            ApiResult apiResult = ApiResult.isSuccess(sResult);
            if (apiResult.getCode() == 1) {  //保存成功   做提交操作
                log.debug("================Save success！！======================");
                String Id = apiResult.getMessage();
                String submitEntity = new Gson().toJson(new SubmitEntity(0L, Id));
                log.debug("submitEntity=============" + submitEntity);
                String submit = client.submit(sFormId, submitEntity);
                ApiResult success = ApiResult.isSuccess(submit);
                if (success.getCode() == 1){ //提交成功   做审核操作
                    log.debug("=========================Submit success！！============================");
                    String auditEntity = new Gson().toJson(new AuditEntity(0L, Id));
                    log.debug("auditEntity=============" + auditEntity);
                    String audit = client.audit(sFormId, auditEntity);
                    ApiResult success1 = ApiResult.isSuccess(audit);
                    if (success1.getCode() == 1){
                        log.debug("==============Audit success！！========================");
                        return new ApiResult(1, SaveSaleOrderMessage.SUCCESS,null);
                    }
                    return new ApiResult(0,SaveSaleOrderMessage.ERROR4,null);
                }
                return new ApiResult(0,SaveSaleOrderMessage.ERROR3,null);
            }
            emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTOAll,"销售订单:"+
                    model.getFBillNo()+"保存金蝶失败","原因:"+sResult);
            return new ApiResult(0,SaveSaleOrderMessage.ERROR2,null);
        }
        return new ApiResult(0,SaveSaleOrderMessage.ERROR1,null);
    }

    public String delete(String billno) throws Exception {
        log.debug("SaleOrder delete billno =============" +billno);
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            String[] split = billno.split(",");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Numbers",split);
            String sContent = new Gson().toJson(jsonObject);
            String unAudit = client.unAudit(sFormId, sContent);
            String delete = client.delete(sFormId, sContent);
            ApiResult success = ApiResult.isSuccess(delete);
            if (success.getCode() == 1) {
                log.debug("delete success =============" +billno);
                return "delete success";
            }
        }
        log.debug("delete false by some reasons");
        return "delete success";
    }

    public JSONObject view(String billno) throws Exception{
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            ViewEntity viewEntity = new ViewEntity();
            viewEntity.setNumber(billno);
            viewEntity.setCreateOrgId("0");
            String view = client.view(sFormId, new Gson().toJson(viewEntity));
//            log.info("view========================="+view);
            return JSONObject.parseObject(view);
        }
        return null;
    }

    public Map<String, Object> findId(String billno) throws Exception{
        Integer id = null;
        String number = null;
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            ViewEntity viewEntity = new ViewEntity();
            viewEntity.setNumber(billno);
            viewEntity.setCreateOrgId("0");
            String view = client.view(sFormId, new Gson().toJson(viewEntity));
//            log.info("view========================="+view);
            JSONObject jsonObject = JSONObject.parseObject(view);
            try {
                id = jsonObject.getJSONObject("Result").getJSONObject("Result").getInteger("Id");
                number = jsonObject.getJSONObject("Result").getJSONObject("Result").getJSONObject("CustId").getString("Number");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String finalNumber = number;
        Integer finalId = id;
        return new HashMap<String,Object>(){{
            put("id",finalId);
            put("number", finalNumber);
        }};
    }

}

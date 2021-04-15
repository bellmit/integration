package com.qunjie.crm.invoice.service;

import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.service.EmailServiceHelper;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.invoice.args.*;
import com.qunjie.crm.invoice.results.InvoiceResult;
import com.qunjie.crm.manager.impl.InvoiceManagerImpl;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import com.qunjie.crm.saleorder.service.SaleOrderService;
import com.qunjie.crm.utils.DefaultValues;
import com.qunjie.jindie.invoice.constants.FieldNameMain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.invoice.service.InvoiceService
 *
 * @author whs
 * Date:   2021/1/20  15:13
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Slf4j
@Service
public class InvoiceService {

    @Autowired
    EmailServiceHelper emailServiceHelper;
    @Autowired
    CrmQueryService crmQueryService;

    private static final String OPERATOR = "EQ";
    public static final String DATAOBJECTAPINAME = DefaultValues.INVOICEAPPLICATIONOBJ;
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //销售订单编号
    public static final String ORDER_ID = "order_id";

    public InvoiceResult invoiceAdd(WorkflowRequestTable workflowRequestTable) throws AccessTokenException {
        InvoiceManagerImpl invoiceManagerImpl = SpringBeanUtils.getBean(InvoiceManagerImpl.class);
        List<WorkflowRequestTableField> mains = workflowRequestTable.getMains();
        InvoiceObjectData invoiceObjectData = new InvoiceObjectData();
        Map<String, String> map = invoiceObjectData.valuesOf(mains);
        String oa_billno = map.get(FieldNameMain.HTBH.name());
        if (StringUtils.isBlank(oa_billno)){
            log.info("========================流程中的销售单号为空！==============================");
        }
        //通过销售编号查找
        SaleOrderService saleOrderService = new SaleOrderService();
        String order_id = null;
        try {
            QueryResult queryResult = saleOrderService.saleOrderQueryBy(oa_billno);
            order_id = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("_id");
            String account_id = queryResult.getData().getJSONArray("dataList").getJSONObject(0).getString("account_id");
            invoiceObjectData.setAccount_id(account_id);
        } catch (Exception e) {
            log.info("=========================crm未查到此oa的销售单号："+oa_billno+"============================================");
            emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTOAll,"开票推送crm失败","时间："+sdf.format(new Date())+"\ncrm中销售订单号为:"+oa_billno+"\n在crm中不存在此销售订单，请查看!!");
            InvoiceResult invoiceResult = new InvoiceResult();
            invoiceResult.setErrorCode(0);
            return invoiceResult;
        }
        Double bckpje = Double.valueOf(map.get(FieldNameMain.BCKPJE.name()));
        if (oa_billno == null || order_id == null || bckpje == null){
            return null;
        }
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.valuesOf(order_id,oa_billno,bckpje);
        invoiceDetails.add(invoiceDetail);
        InvoiceProductObj invoiceProductObj = new InvoiceProductObj(invoiceDetails);
        InvoiceModel invoiceModel = new InvoiceModel(invoiceObjectData,invoiceProductObj);
        return invoiceManagerImpl.saveInvoice(invoiceModel);
    }

    public BaseResult invoiceDelete(String dataId) throws AccessTokenException {
        InvoiceManagerImpl invoiceManager = SpringBeanUtils.getBean(InvoiceManagerImpl.class);
        InvoiceDeleteDataArg.InvoiceDeleteModel data = new InvoiceDeleteDataArg.InvoiceDeleteModel();
        List<String> list = new ArrayList<>();
        list.add(dataId);
        data.setIdList(list);
        return invoiceManager.deleteInvoice(data);
    }

    public BaseResult invoiceInvalid(String dataId) throws AccessTokenException{
        InvoiceManagerImpl invoiceManager = SpringBeanUtils.getBean(InvoiceManagerImpl.class);
        InvoiceInvalidArg.InvoiceInvalidData data = new InvoiceInvalidArg.InvoiceInvalidData();
        data.setObject_data_id(dataId);
        return invoiceManager.invalidInvoice(data);
    }

    public QueryResult InvoiceQueryBy(Map<String,String> map) throws AccessTokenException {
        QueryResult query = crmQueryService.QueryBy(map,DATAOBJECTAPINAME);
        return query;
    }
}

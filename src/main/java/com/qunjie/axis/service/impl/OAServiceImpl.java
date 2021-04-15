package com.qunjie.axis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qunjie.axis.model.WorkflowId;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.axis.service.OAService;
import com.qunjie.axis.service.invokeWebservice.InvokeFanWeiGetWorkflowRequestWebservice;
import com.qunjie.axis.utils.WorkflowResponseToBean;
import com.qunjie.common.consts.DeleteSaleOrderMessage;
import com.qunjie.common.consts.SaveSaleOrderMessage;
import com.qunjie.common.response.ApiResult;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.achievement.service.AchievementCrmService;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.confirmAchievement.service.ConfirmAchievementService;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.huikuan.service.CrmHuikuanService;
import com.qunjie.crm.invoice.results.InvoiceResult;
import com.qunjie.crm.invoice.service.InvoiceService;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.saleTarget.event.SaleTargetEvent;
import com.qunjie.crm.saleorder.results.SaleOrderResult;
import com.qunjie.crm.saleorder.service.SaleOrderService;
import com.qunjie.jindie.huikuan.service.HuikuanService;
import com.qunjie.jindie.invoice.service.InvoiceSaveService;
import com.qunjie.jindie.saleorder.save.enums.FieldName;
import com.qunjie.jindie.saleorder.save.enums.QDXSDDFieldName;
import com.qunjie.jindie.saleorder.save.service.SaveService;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class OAServiceImpl implements OAService {

    private static Logger log = LoggerFactory.getLogger(OAServiceImpl.class);
    @Override
    public String SaveSaleOrder(String requestid) throws Exception {

        String result = "";
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        com.alibaba.fastjson.JSONObject objects = JSON.parseObject(result);
        Integer workflowId = WorkflowResponseToBean.getWorkflowId(result);
        if (objects.isEmpty() || workflowId == null) {
            return new ApiResult(0, "requestid = " + requestid + "=====泛微接口返回值为空活流程workflowId为空", null).toString();
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        if (workflowRequestTable != null && IsCancelled(workflowRequestTable.getMains())){
            return new ApiResult(1, SaveSaleOrderMessage.SUCCESS, null).toString();
        }
        /**
         * 推送crm
         * 推送金蝶
         * 两者都成功则OK，否则失败，如果crm成功，金蝶失败，删除crm数据
         */
        log.info("销售订单推送crm=================start");
        SaleOrderService saleOrderService = SpringBeanUtils.getBean(SaleOrderService.class);
        SaleOrderResult saleOrderResult = saleOrderService.saleOrderAdd(workflowRequestTable,workflowId);
        if (saleOrderResult == null || saleOrderResult.getErrorCode() != 0){
            return new ApiResult(0,"crm销售订单数据推送失败","").toString();
        }else {
            String dataId = saleOrderResult.getDataId();
            log.info("crm推送成功=======end==========销售订单推送金蝶=================start");
            SaveService saveService = SpringBeanUtils.getBean(SaveService.class);
            ApiResult save = saveService.save(workflowRequestTable,workflowId);
            if (save != null && save.getCode() == 1){
                log.info("=========销售订单保存成功!=========");
                if (saleOrderResult.getSaleTargetEvent() != null){
                    ApplicationContext applicationContext = SpringBeanUtils.getApplicationContext();
                    applicationContext.publishEvent(saleOrderResult.getSaleTargetEvent());
                }
                return save.toString();
            }else {
                saleOrderService.saleOrderInvalid(dataId);
                saleOrderService.saleOrderDelete(dataId);
                save.setMessage("销售订单推送金蝶失败,crm中数据已删除");
                return save.toString();
            }
        }
    }

    @Override
    public String DeleteSaleOrder(String requestid) throws Exception {
        String result = "";
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        com.alibaba.fastjson.JSONObject objects = JSON.parseObject(result);
        if (objects.isEmpty()) {
            return DeleteSaleOrderMessage.SUCCESS;
        }

        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        List<WorkflowRequestTableField> workflowRequestTableFieldMains = workflowRequestTable.getMains();
        Integer workflowId = WorkflowResponseToBean.getWorkflowId(result);
        if (CollectionUtils.isEmpty(workflowRequestTableFieldMains) || workflowId == null) {
            return DeleteSaleOrderMessage.SUCCESS;
        }
        String billno = "";
        if (WorkflowId.QDXSDD.contains(workflowId)){
            billno = getBillnoQDXSDD(workflowRequestTableFieldMains);
        }else {
            billno = getBillno(workflowRequestTableFieldMains);
        }
        String delete = null;
        try {
            log.info("====================删除金蝶销售订单=========="+billno+"=================");
            SaveService saveService = SpringBeanUtils.getBean(SaveService.class);
            delete = saveService.delete(billno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            log.info("=====================删除crm销售订单==========="+billno+"===================");
            SaleOrderService saleOrderService = SpringBeanUtils.getBean(SaleOrderService.class);
            QueryResult queryResult = saleOrderService.saleOrderQueryBy(billno);
            if (queryResult != null && queryResult.getErrorCode() == 0){
                JSONArray dataList = queryResult.getData().getJSONArray("dataList");
                if (dataList != null && dataList.size() > 0){
                    for (int i = 0; i < dataList.size(); i++) {
                        com.alibaba.fastjson.JSONObject jsonObject = dataList.getJSONObject(i);
                        String id = String.valueOf(jsonObject.get("_id"));
                        if (!StringUtils.isBlank(id)){
                            saleOrderService.saleOrderInvalid(id);
                            saleOrderService.saleOrderDelete(id);
                            try {
                                SaleTargetEvent saleTargetData = saleOrderService.getSaleTargetData(workflowRequestTable, workflowId, -1.0);
                                if (saleTargetData != null) {
                                    ApplicationContext bean = SpringBeanUtils.getApplicationContext();
                                    bean.publishEvent(saleTargetData);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (AccessTokenException e) {
            e.printStackTrace();
        }
        return delete;
    }

    @Override
    public int SaveInvoice(String requestid) throws Exception {
        String result = "";
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        if (StringUtils.isBlank(result)){
            return 0;
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        /**
         * (如果不是因为销售单号不存在)而导致的crm保存失败，返回失败0；泛微流程无法往下流转
         * 如果crm保存成功，而金蝶保存失败(不是因为销售单号不存在)，crm中的开票作废并删除
         * crm保存成功(销售单号不存在，不用保存，认为是成功)，金蝶保存成功(销售单号不存在，不用保存，认为是成功)，流程正常往下流转
         */
        InvoiceService invoiceService = SpringBeanUtils.getBean(InvoiceService.class);
        log.info("开票保存crm============================start");
        InvoiceResult invoiceResult = invoiceService.invoiceAdd(workflowRequestTable);
        if (invoiceResult != null && invoiceResult.getErrorCode() == 0){
            String dataId = invoiceResult.getDataId();
            InvoiceSaveService invoiceSaveService = SpringBeanUtils.getBean(InvoiceSaveService.class);
            log.info("开票保存金蝶============================start");
            int save = invoiceSaveService.save(workflowRequestTable);
            if (save == 1){
                log.info("开票信息保存成功============================end");
                return 1;
            }else {
                log.info("开票信息保存失败============================删除crm中开票信息:" + dataId);
                if (!StringUtils.isBlank(dataId)){
                    BaseResult baseResult = invoiceService.invoiceInvalid(dataId);
                    if (baseResult != null && baseResult.getErrorCode() == 0)
                    invoiceService.invoiceDelete(dataId);
                }
            }
        }
        return 0;
    }

    @Override
    public int SavePayment(String requestid) throws Exception {
        String result = "";
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        if (StringUtils.isBlank(result)){
            return 0;
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        CrmHuikuanService crmHuikuanService = SpringBeanUtils.getBean(CrmHuikuanService.class);
        ApiResult apiResult = crmHuikuanService.huikuanAdd(workflowRequestTable);
        if (apiResult != null && apiResult.getCode() == 1){
            HuikuanService huikuanService = SpringBeanUtils.getBean(HuikuanService.class);
            int save = huikuanService.save(workflowRequestTable);
            if (save == 1){
                log.info("收款单信息保存成功============================end");
                return 1;
            }else {
                log.info("收款单信息保存金蝶失败============================删除crm中保存成功的单据");
                if (apiResult.getData() != null && apiResult.getData() instanceof List){
                    List<String> data = (List<String>) apiResult.getData();
                    log.info("=============================删除crm中回款单========================"+data);
                    for (String k : data) {
                        try {
                            BaseResult baseResult = crmHuikuanService.huikuanInvalid(k);
                            if (baseResult != null && baseResult.getErrorCode() == 0){
                                crmHuikuanService.huikuanDelete(k);
                            }
                        } catch (AccessTokenException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int SaveAchievement(String requestid) throws Exception {
        String result = "";
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        AchievementCrmService achievementCrmService = SpringBeanUtils.getBean(AchievementCrmService.class);
        ApiResult apiResult = achievementCrmService.AchievementAdd(workflowRequestTable);
        return apiResult.getCode();
    }

    @Override
    public int SaveConfirmAchievement(String requestid) throws Exception {
        String result = "";
        if (StringUtils.isNotBlank(requestid)) {
            JSONObject response = InvokeFanWeiGetWorkflowRequestWebservice.invokeGetWorkflowRequestWebservice(requestid);
            result = response.toString();
        }
        WorkflowRequestTable workflowRequestTable = WorkflowResponseToBean.responseToBean(result);
        ConfirmAchievementService bean = SpringBeanUtils.getBean(ConfirmAchievementService.class);
        ApiResult apiResult = bean.ConfirmAchievementAdd(workflowRequestTable);
        return apiResult.getCode();
    }

    private String getBillno(List<WorkflowRequestTableField> list) {
        String fieldValue = "";
        for (WorkflowRequestTableField e : list) {
            if (FieldName.BILLNO == FieldName.valuesOf(e.getFieldName())) {
                fieldValue = e.getFieldValue();
            }
        }
        return fieldValue;
    }

    private String getBillnoQDXSDD(List<WorkflowRequestTableField> list) {
        String fieldValue = "";
        for (WorkflowRequestTableField e : list) {
            if (QDXSDDFieldName.DH == QDXSDDFieldName.valuesOf(e.getFieldName())) {
                fieldValue = e.getFieldValue();
            }
        }
        return fieldValue;
    }

    private Boolean IsCancelled(List<WorkflowRequestTableField> list) {
        String fieldValue = "";
        if (!CollectionUtils.isEmpty(list)) {
            for (WorkflowRequestTableField e : list) {
                FieldName fieldName = FieldName.valuesOf(e.getFieldName());
                if (FieldName.HTZT == fieldName) {
                    fieldValue = e.getFieldValue();
                }
            }
        }
        if ("1".equals(fieldValue)) {
            return true;
        }
        return false;
    }

}

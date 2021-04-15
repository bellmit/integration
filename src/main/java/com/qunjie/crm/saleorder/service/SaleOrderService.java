package com.qunjie.crm.saleorder.service;
/**
 * Created by whs on 2021/1/12.
 */


import cn.hutool.core.date.DateUtil;
import com.qunjie.axis.model.WorkflowId;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.service.EmailServiceHelper;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.impl.SaleOrderManagerImpl;
import com.qunjie.crm.query.args.QueryFilterField;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import com.qunjie.crm.saleTarget.event.SaleTargetEvent;
import com.qunjie.crm.saleTarget.model.SaleTargetModel;
import com.qunjie.crm.saleorder.args.*;
import com.qunjie.crm.saleorder.results.SaleOrderResult;
import com.qunjie.jindie.saleorder.save.enums.FieldName;
import com.qunjie.jindie.saleorder.save.enums.QDXSDDFieldName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.service.SaleOrderService
 *
 * @author whs
 *         Date:   2021/1/12  14:10
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Service
public class SaleOrderService {

    @Autowired
    private EmailServiceHelper emailServiceHelper;

    private static final String OPERATOR = "EQ";
    public static final String DATAOBJECTAPINAME = "SalesOrderObj";
    //oa销售订单编号
    public static final String FIELD_CLVED__C = "field_Clved__c";

    public SaleOrderResult saleOrderAdd(WorkflowRequestTable workflowRequestTable,Integer workflowid) throws AccessTokenException {
        SaleOrderManagerImpl saleOrderManagerImpl = SpringBeanUtils.getBean(SaleOrderManagerImpl.class);
        List<WorkflowRequestTableField> mains = workflowRequestTable.getMains();
        SaleOrderObjectData saleOrderObjectData = new SaleOrderObjectData();
        Map<String, String> map = _valueOfObjectData(saleOrderObjectData, workflowid, mains);
        List<List<WorkflowRequestTableField>> details = workflowRequestTable.getDetails();
        List<SaleOrderDetail> saleOrderDetails = new ArrayList<>();
        if (!CollectionUtils.isEmpty(details)){
            details.forEach(e->{
                SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
                if (WorkflowId.QDXSDD.contains(workflowid)){
                    saleOrderDetail.QDXSDDvaluesOf(e);
                }else {
                    saleOrderDetail.valuesOf(e);
                }
                saleOrderDetails.add(saleOrderDetail);
            });
        }
        SalesOrderProductObj salesOrderProductObj = new SalesOrderProductObj(saleOrderDetails);
        SaleOrderModel saleOrderModel = new SaleOrderModel(saleOrderObjectData,salesOrderProductObj);
        SaleOrderResult saleOrderResult = saleOrderManagerImpl.saveSaleOrder(saleOrderModel);
        if (saleOrderResult.getErrorCode() != 0){
            emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTOAll,"销售订单:"+
                    saleOrderObjectData.getField_Clved__c()+"保存crm失败","原因:"+saleOrderResult.getErrorMessage());
        }else {
            SaleTargetEvent saleTargetEvent = _getSaleTargetData(saleOrderObjectData, workflowid, map, 1.0);
            saleOrderResult.setSaleTargetEvent(saleTargetEvent);
        }
        return saleOrderResult;
    }

    private Map<String, String> _valueOfObjectData(SaleOrderObjectData saleOrderObjectData,Integer workflowid,List<WorkflowRequestTableField> mains){
        Map<String,String> map ;
        if (WorkflowId.QDXSDD.contains(workflowid)){
            map = saleOrderObjectData.QDXSDDvaluesOf(mains);
        }else {
            map = saleOrderObjectData.valuesOf(mains);
        }
        return map;
    }

    private SaleTargetEvent _getSaleTargetData(SaleOrderObjectData saleOrderObjectData,Integer workflowid,Map<String,String> map,Double value){
        Date date = new Date(Long.valueOf(saleOrderObjectData.getOrder_time()));
        String year = String.valueOf(DateUtil.year(date));
        String month = String.valueOf(DateUtil.month(date) + 1);
        String sname, team, area, lx;
        if (WorkflowId.QDXSDD.contains(workflowid)){
            sname = map.get(QDXSDDFieldName.XSYXM.name());
            team = map.get(QDXSDDFieldName.QDJLBM.name());
            area = map.get(QDXSDDFieldName.QDJLSZDQ.name());
            lx = map.get(QDXSDDFieldName.QYLX.name());
        }else{
            sname = map.get(FieldName.QYR.name());
            team = map.get(FieldName.XSYBM.name());
            area = map.get(FieldName.XSYSZDQ.name());
            lx = map.get(FieldName.HTLX.name());
        }
        if ("0".equals(lx)){
            SaleTargetModel saleTargetModel = new SaleTargetModel();
            saleTargetModel.setField_q4r9M__c(value);
            SaleTargetEvent saleTargetEvent = new SaleTargetEvent(this,saleTargetModel,year,month,sname,team,area);
            return saleTargetEvent;
        }
        return null;
    }
    public SaleTargetEvent getSaleTargetData(WorkflowRequestTable workflowRequestTable,Integer workflowid,Double value){
        SaleOrderObjectData saleOrderObjectData = new SaleOrderObjectData();
        Map<String, String> map = _valueOfObjectData(saleOrderObjectData, workflowid, workflowRequestTable.getMains());
        return _getSaleTargetData(saleOrderObjectData,workflowid,map,value);
    }

    public BaseResult saleOrderDelete(String dataId) throws AccessTokenException {
        SaleOrderManagerImpl saleOrderManager = SpringBeanUtils.getBean(SaleOrderManagerImpl.class);
        SaleOrderDeleteDataArg.SaleOrderDeleteModel data = new SaleOrderDeleteDataArg.SaleOrderDeleteModel();
        List<String> list = new ArrayList<>();
        list.add(dataId);
        data.setIdList(list);
        return saleOrderManager.deleteSaleOrder(data);
    }

    public BaseResult saleOrderInvalid(String dataId) throws AccessTokenException{
        SaleOrderManagerImpl saleOrderManager = SpringBeanUtils.getBean(SaleOrderManagerImpl.class);
        SaleOrderInvalidArg.SaleOrderInvalidData data = new SaleOrderInvalidArg.SaleOrderInvalidData();
        data.setObject_data_id(dataId);
        return saleOrderManager.invalidSaleOrder(data);
    }

    /**
     * 通过OA销售订单号查询crm中销售订单
     * @param value
     * @return
     * @throws AccessTokenException
     */
    public QueryResult saleOrderQueryBy(String value) throws AccessTokenException {
        CrmQueryService crmQueryService = SpringBeanUtils.getBean(CrmQueryService.class);
        List<QueryFilterField> list = new ArrayList<>();
        QueryFilterField queryFilterField = new QueryFilterField();
        queryFilterField.setOperator(OPERATOR);
        queryFilterField.setField_name(FIELD_CLVED__C);
        List<String> fieldValues = new ArrayList<>();
        fieldValues.add(value);
        queryFilterField.setField_values(fieldValues);
        list.add(queryFilterField);
        QueryResult query = crmQueryService.query(DATAOBJECTAPINAME, list);
        return query;
    }
}

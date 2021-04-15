package com.qunjie.axis.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qunjie.axis.model.WorkflowRequestTable;
import com.qunjie.axis.model.WorkflowRequestTableField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.axis.utils.WorkflowResponseToBean
 *
 * @author whs
 * Date:   2021/1/18  16:56
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class WorkflowResponseToBean {

    private static Logger log = LoggerFactory.getLogger(WorkflowResponseToBean.class);

    public static WorkflowRequestTable responseToBean(String result){
        List<List<WorkflowRequestTableField>> workflowRequestTableField1DetailLists = new ArrayList<>();
        /**
         * 此处WorkflowRequestTableRecord，当明细数量为1时，返回的时对象类型，大于1时返回的数组类型，两者互斥，
         * 直接try-catch，
         * 把没异常的数据加入workflowRequestTableField1DetailLists
         */
        //订单明细多条
        try {
            JSONArray jsonArray = JSON.parseObject(result).getJSONObject("ns1:getWorkflowRequestResponse")
                    .getJSONObject("ns1:out")
                    .getJSONObject("workflowDetailTableInfos")
                    .getJSONObject("WorkflowDetailTableInfo")
                    .getJSONObject("workflowRequestTableRecords")
                    .getJSONArray("WorkflowRequestTableRecord");
            if (null != jsonArray && jsonArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    List<WorkflowRequestTableField> workflowRequestTableFields = com.alibaba.fastjson.JSONObject.parseArray(jsonArray.getJSONObject(i)
                            .getJSONObject("workflowRequestTableFields")
                            .getJSONArray("WorkflowRequestTableField").toJSONString(), WorkflowRequestTableField.class);
                    workflowRequestTableField1DetailLists.add(workflowRequestTableFields);
                }
            }
        } catch (Exception e) {
            log.debug("=============================订单明细解析1失败===============================");
        }
        //订单明细为单条
        try {
            List<WorkflowRequestTableField> workflowRequestTableFields = com.alibaba.fastjson.JSONObject.parseArray(JSON.parseObject(result).getJSONObject("ns1:getWorkflowRequestResponse")
                    .getJSONObject("ns1:out")
                    .getJSONObject("workflowDetailTableInfos")
                    .getJSONObject("WorkflowDetailTableInfo")
                    .getJSONObject("workflowRequestTableRecords")
                    .getJSONObject("WorkflowRequestTableRecord")
                    .getJSONObject("workflowRequestTableFields")
                    .getJSONArray("WorkflowRequestTableField").toJSONString(), WorkflowRequestTableField.class);
            workflowRequestTableField1DetailLists.add(workflowRequestTableFields);
        } catch (Exception e) {
            log.debug("=============================订单明细解析2失败===============================");
        }

        /**
         * 此处WorkflowRequestTableRecord，当明细数量为1时，返回的时对象类型，大于1时返回的数组类型，两者互斥，
         * 直接try-catch，
         * 把没异常的数据加入workflowRequestTableField1DetailLists
         */
        //订单明细多条
        try {
            JSONArray jsonArray = JSON.parseObject(result).getJSONObject("ns1:getWorkflowRequestResponse")
                    .getJSONObject("ns1:out")
                    .getJSONObject("workflowDetailTableInfos")
                    .getJSONArray("WorkflowDetailTableInfo")
                    .getJSONObject(0)
                    .getJSONObject("workflowRequestTableRecords")
                    .getJSONArray("WorkflowRequestTableRecord");
            if (null != jsonArray && jsonArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    List<WorkflowRequestTableField> workflowRequestTableFields = com.alibaba.fastjson.JSONObject.parseArray(jsonArray.getJSONObject(i)
                            .getJSONObject("workflowRequestTableFields")
                            .getJSONArray("WorkflowRequestTableField").toJSONString(), WorkflowRequestTableField.class);
                    workflowRequestTableField1DetailLists.add(workflowRequestTableFields);
                }
            }
        } catch (Exception e) {
            log.debug("=============================订单明细解析3失败===============================");
        }

        //订单明细为单条
        try {
            List<WorkflowRequestTableField> workflowRequestTableFields = com.alibaba.fastjson.JSONObject.parseArray(JSON.parseObject(result).getJSONObject("ns1:getWorkflowRequestResponse")
                    .getJSONObject("ns1:out")
                    .getJSONObject("workflowDetailTableInfos")
                    .getJSONArray("WorkflowDetailTableInfo")
                    .getJSONObject(0)
                    .getJSONObject("workflowRequestTableRecords")
                    .getJSONObject("WorkflowRequestTableRecord")
                    .getJSONObject("workflowRequestTableFields")
                    .getJSONArray("WorkflowRequestTableField").toJSONString(), WorkflowRequestTableField.class);
            workflowRequestTableField1DetailLists.add(workflowRequestTableFields);
        } catch (Exception e) {
            log.debug("=============================订单明细解析4失败===============================");
        }

        List<WorkflowRequestTableField> workflowRequestTableFieldMains = null;
        try {
            workflowRequestTableFieldMains = com.alibaba.fastjson.JSONObject.parseArray(
                    JSON.parseObject(result).getJSONObject("ns1:getWorkflowRequestResponse")
                            .getJSONObject("ns1:out")
                            .getJSONObject("workflowMainTableInfo")
                            .getJSONObject("requestRecords")
                            .getJSONObject("WorkflowRequestTableRecord")
                            .getJSONObject("workflowRequestTableFields")
                            .getJSONArray("WorkflowRequestTableField")
                            .toJSONString(), WorkflowRequestTableField.class);
        } catch (Exception e) {
            workflowRequestTableFieldMains = new ArrayList<>();
        }
        return new WorkflowRequestTable(workflowRequestTableField1DetailLists, workflowRequestTableFieldMains);
    }

    public static Integer getWorkflowId(String result){
        Integer id = null;
        try {
            id = JSON.parseObject(result).getJSONObject("ns1:getWorkflowRequestResponse")
                    .getJSONObject("ns1:out")
                    .getJSONObject("workflowBaseInfo")
                    .getInteger("workflowId");
        } catch (Exception e) {
            log.debug("=============================订单明细为单条===============================");
        }
        return id;
    }
}

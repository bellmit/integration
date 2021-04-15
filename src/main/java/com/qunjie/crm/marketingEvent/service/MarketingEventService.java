package com.qunjie.crm.marketingEvent.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.marketingEvent.model.MarketingEventModel;
import com.qunjie.crm.query.args.QueryFilterField;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.marketingEvent.service.MarketingEventService
 *
 * @author whs
 * Date:   2021/1/26  14:54
 * Description:     市场活动
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class MarketingEventService {

    @Autowired
    private CrmQueryService crmQueryService;

    public static final String dataObjectApiName = "MarketingEventObj";

    public List<MarketingEventModel> query(String _id) throws AccessTokenException {
        List<String> ids = new ArrayList<>();
        ids.add(_id);
        List<QueryFilterField> filters = new ArrayList<>();
        QueryFilterField queryFilterField = new QueryFilterField();
        queryFilterField.setOperator("EQ");
        queryFilterField.setField_name("_id");
        queryFilterField.setField_values(ids);
        filters.add(queryFilterField);
        QueryResult query = crmQueryService.query(dataObjectApiName, filters);
        List<MarketingEventModel> returnList = null;
        if (query.getErrorCode() == 0){
            JSONObject data = query.getData();
            if (!data.isEmpty()){
                JSONArray jsonArray = data.getJSONArray("dataList");
                if (jsonArray != null && jsonArray.size() > 0){
                    returnList = JSON.parseArray(new Gson().toJson(jsonArray), MarketingEventModel.class);
                }
            }
        }
        return returnList;
    }

    public List<MarketingEventModel> query(String id,String name) throws AccessTokenException {

        List<QueryFilterField> filters = new ArrayList<>();
        QueryFilterField queryFilterField = new QueryFilterField();
        if (!StringUtils.isBlank(id)) {
            List<String> ids = new ArrayList<>();
            ids.add(name);
            queryFilterField.setOperator("EQ");
            queryFilterField.setField_name("_id");
            queryFilterField.setField_values(ids);
            filters.add(queryFilterField);
        }
        if (!StringUtils.isBlank(name)){
            queryFilterField.setOperator("EQ");
            queryFilterField.setField_name("name");
            queryFilterField.setField_values(Arrays.asList(name));
            filters.add(queryFilterField);
        }
        QueryResult query = crmQueryService.query(dataObjectApiName, filters);
        List<MarketingEventModel> returnList = null;
        if (query.getErrorCode() == 0){
            JSONObject data = query.getData();
            if (!data.isEmpty()){
                JSONArray jsonArray = data.getJSONArray("dataList");
                if (jsonArray != null && jsonArray.size() > 0){
                    returnList = JSON.parseArray(new Gson().toJson(jsonArray), MarketingEventModel.class);
                }
            }
        }
        return returnList;
    }

}

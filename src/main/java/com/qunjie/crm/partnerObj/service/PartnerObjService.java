package com.qunjie.crm.partnerObj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.partnerObj.model.PartnerObjModel;
import com.qunjie.crm.query.args.QueryFilterField;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.account.service.AccountService
 *
 * @author whs
 * Date:   2021/1/14  10:57
 * Description:  合作伙伴
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class PartnerObjService {

    public static final String DATAOBJECTAPINAME = "PartnerObj";
    public static final String OPERATOR = "EQ";
    public static final String NAME = "name";

    @Autowired
    private CrmQueryService crmQueryService;

    public List<PartnerObjModel> queryPartnerObjByName(String name) throws AccessTokenException {
        List<QueryFilterField> list = new ArrayList<>();
        QueryFilterField queryFilterField = new QueryFilterField();
        queryFilterField.setOperator(OPERATOR);
        queryFilterField.setField_name(NAME);
        List<String> fieldValues = new ArrayList<>();
        fieldValues.add(name);
        queryFilterField.setField_values(fieldValues);
        list.add(queryFilterField);
        List<PartnerObjModel> partnerObjModels= null;
        QueryResult query = crmQueryService.query(DATAOBJECTAPINAME, list);
        if (query.getErrorCode() == 0){
            JSONObject data = query.getData();
            if (!data.isEmpty()){
                JSONArray jsonArray = data.getJSONArray("dataList");
                if (jsonArray != null && jsonArray.size() > 0){
                    partnerObjModels = JSON.parseArray(new Gson().toJson(jsonArray), PartnerObjModel.class)
                            .stream().filter(e -> e.getLock_status() == 0).collect(Collectors.toList());
                }
            }
        }
        return partnerObjModels;
    }
}

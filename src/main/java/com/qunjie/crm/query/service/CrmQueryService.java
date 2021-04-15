package com.qunjie.crm.query.service;

import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.impl.AddressBookManagerImpl;
import com.qunjie.crm.query.args.QueryData;
import com.qunjie.crm.query.args.QueryFilterField;
import com.qunjie.crm.query.args.SearchQueryInfo;
import com.qunjie.crm.query.results.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.query.service.CrmQueryService
 *
 * @author whs
 * Date:   2021/1/14  9:36
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class CrmQueryService {

    @Autowired
    AddressBookManagerImpl addressBookManager;

    public QueryResult query(String dataObjectApiName, List<QueryFilterField> filters) throws AccessTokenException {

        QueryData queryData = new QueryData();
        queryData.setDataObjectApiName(dataObjectApiName);
        SearchQueryInfo searchQueryInfo = new SearchQueryInfo();
        searchQueryInfo.setFilters(filters);
        queryData.setSearch_query_info(searchQueryInfo);
        return addressBookManager.queryData(queryData);
    }

    public QueryResult queryCustom(String dataObjectApiName, List<QueryFilterField> filters) throws AccessTokenException {

        QueryData queryData = new QueryData();
        queryData.setDataObjectApiName(dataObjectApiName);
        SearchQueryInfo searchQueryInfo = new SearchQueryInfo();
        searchQueryInfo.setFilters(filters);
        queryData.setSearch_query_info(searchQueryInfo);
        return addressBookManager.queryCustomData(queryData);
    }

    public QueryResult QueryBy(Map<String,String> map,String DATAOBJECTAPINAME) throws AccessTokenException {
        List<QueryFilterField> list = new ArrayList<>();
        addFilter(map,list);
        QueryResult query = this.query(DATAOBJECTAPINAME, list);
        return query;
    }


    public QueryResult QueryCustomBy(Map<String,String> map,String DATAOBJECTAPINAME) throws AccessTokenException {
        List<QueryFilterField> list = new ArrayList<>();
        addFilter(map,list);
        QueryResult query = this.queryCustom(DATAOBJECTAPINAME, list);
        return query;
    }

    private void addFilter(Map<String,String> map,List<QueryFilterField> list){
        map.forEach((k,v)->{
            QueryFilterField queryFilterField = new QueryFilterField();
            queryFilterField.setOperator("EQ");
            queryFilterField.setField_name(k);
            List<String> fieldValues = new ArrayList<>();
            fieldValues.add(v);
            queryFilterField.setField_values(fieldValues);
            list.add(queryFilterField);
        });
    }
}

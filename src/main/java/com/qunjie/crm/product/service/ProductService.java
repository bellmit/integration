package com.qunjie.crm.product.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.product.model.ProductModel;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import com.qunjie.crm.query.args.QueryFilterField;
import com.qunjie.crm.utils.DefaultValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.product.service.ProductService
 *
 * @author whs
 * Date:   2021/1/14  9:35
 * Description:     产品
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class ProductService {

    public static final String DATAOBJECTAPINAME = "ProductObj";
    public static final String OPERATOR = "EQ";
    public static final String PRODUCT_CODE = "product_code";
    public static final String CURRENTOPENUSERID = DefaultValues.CURRENTOPENUSERID;

    @Autowired
    private CrmQueryService crmQueryService;

    public List<ProductModel> queryProductsByProductCode(String product_code) throws AccessTokenException {
        List<QueryFilterField> list = new ArrayList<>();
        QueryFilterField queryFilterField = new QueryFilterField();
        queryFilterField.setOperator(OPERATOR);
        queryFilterField.setField_name(PRODUCT_CODE);
        List<String> fieldValues = new ArrayList<>();
        fieldValues.add(product_code);
        queryFilterField.setField_values(fieldValues);
        list.add(queryFilterField);
        List<ProductModel> productModels= null;
        QueryResult query = crmQueryService.query(DATAOBJECTAPINAME, list);
        if (query.getErrorCode() == 0){
            JSONObject data = query.getData();
            if (!data.isEmpty()){
                JSONArray jsonArray = data.getJSONArray("dataList");
                if (jsonArray != null && jsonArray.size() > 0){
                    productModels = JSON.parseArray(new Gson().toJson(jsonArray), ProductModel.class);
                }
            }
        }
    return productModels;
    }
}

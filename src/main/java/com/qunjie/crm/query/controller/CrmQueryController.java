package com.qunjie.crm.query.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.query.controller.CrmQueryController
 *
 * @author whs
 * Date:   2021/3/18  15:29
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/crm/query")
public class CrmQueryController {
    @Autowired CrmQueryService crmQueryService;

    @PostMapping("/query")
    public QueryResult query(@RequestBody JSONObject jsonObject) throws AccessTokenException {
        JSONObject json = jsonObject.getJSONObject("map");
        Map map = JSON.parseObject(json.toJSONString(), Map.class);
        QueryResult api = crmQueryService.QueryBy(map, jsonObject.getString("api"));
        return api;
    }

    @PostMapping("/queryCustom")
    public QueryResult queryCustom(@RequestBody JSONObject jsonObject) throws AccessTokenException {
        JSONObject json = jsonObject.getJSONObject("map");
        Map map = JSON.parseObject(json.toJSONString(), Map.class);
        QueryResult api = crmQueryService.QueryCustomBy(map, jsonObject.getString("api"));
        return api;
    }
}

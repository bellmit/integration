package com.qunjie.crm.saleorder.controller;
/**
 * Created by whs on 2021/1/12.
 */

import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import com.qunjie.crm.saleorder.service.SaleOrderService;
import com.qunjie.crm.utils.DefaultValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.controller.SaleOrderController
 *
 * @author whs
 *         Date:   2021/1/12  15:13
 *         Description:     操作crm销售订单
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/saleorder")
public class CrmSaleOrderController {

    @Autowired
    private Test test;
    @Autowired
    private CrmQueryService crmQueryService;
    @Autowired
    SaleOrderService saleOrderService;

    @GetMapping("/saveSaleOrder")
    public int test(String requestid)  {
        System.out.println("=============");
        try {
            test.test(requestid);
        } catch (AccessTokenException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @GetMapping("/deleteSaleOrder")
    public int delete(String dataId) throws AccessTokenException {
        BaseResult baseResult = saleOrderService.saleOrderDelete(dataId);
        System.out.println(baseResult);
        return 1;
    }

    @GetMapping("/invalidSaleOrder")
    public int invalid(String dataId) throws AccessTokenException{
        BaseResult baseResult = saleOrderService.saleOrderInvalid(dataId);
        System.out.println(baseResult);
        return 1;
    }

    @GetMapping("getSaleOrder")
    public QueryResult getSaleOrder(String name) throws AccessTokenException {
        QueryResult queryResult = saleOrderService.saleOrderQueryBy(name);
        return queryResult;
    }

    @PostMapping("querySaleOrder")
    public QueryResult getSaleOrder(@RequestBody Map<String,String> map) throws AccessTokenException {
        QueryResult queryResult = crmQueryService.QueryBy(map, DefaultValues.SALESORDEROBJ);
        return queryResult;
    }
}

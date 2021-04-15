package com.qunjie.crm.product.controller;

import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.product.model.ProductModel;
import com.qunjie.crm.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.product.controller.TestController
 *
 * @author whs
 * Date:   2021/1/14  10:11
 * Description:     销售产品
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/product")
public class ProductTestController {

    @Autowired
    ProductService productService;
    @GetMapping("query")
    public ProductModel test(String code) throws AccessTokenException {
        List<ProductModel> productModels = productService.queryProductsByProductCode(code);
        return CollectionUtils.isEmpty(productModels) ? null : productModels.get(0);
    }
}

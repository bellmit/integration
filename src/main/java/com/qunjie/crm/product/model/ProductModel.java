package com.qunjie.crm.product.model;

import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.product.model.ProductModel
 *
 * @author whs
 * Date:   2021/1/14  9:45
 * Description: 产品
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class ProductModel {

    private Boolean is_saleable;

    private String name;

    private String _id;

    private String product_code;

    private Boolean is_deleted;
}

package com.qunjie.crm.saleorder.args;
/**
 * Created by whs on 2021/1/12.
 */

import com.qunjie.jindie.saleorder.save.enums.FieldName;
import com.qunjie.axis.model.WorkflowRequestTableField;
import com.qunjie.common.annotation.Describe;
import com.qunjie.common.util.Arith;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.product.model.ProductModel;
import com.qunjie.crm.product.service.ProductService;
import com.qunjie.jindie.saleorder.save.enums.QDXSDDFieldName;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.crm.saleorder.args.SaleOrderDetail
 *
 * @author whs
 *         Date:   2021/1/12  11:22
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class SaleOrderDetail {

    @Describe("订单产品编号")
    private String name;

    @Describe("数量")
    private String quantity = "0";

    @Describe("价格(元）")
    private String product_price = "0.00";

    @Describe("小计")
    private String subtotal;

    @Describe("销售单价（元）")
    private String sales_price = "0.00";

    @Describe("产品名称")
    private String product_id;

    public void valuesOf(List<WorkflowRequestTableField> details){
        if (CollectionUtils.isEmpty(details)){
            return;
        }
        details.forEach(e->{
            if (!StringUtils.isBlank(e.getFieldName()) && null != FieldName.valuesOf(e.getFieldName())){
                switch (FieldName.valuesOf(e.getFieldName())){
                    case JHRQ:
                        break;
                    case CPBM:
                        productId(e.getFieldValue());
                        break;
                    case SL:
                        this.quantity = e.getFieldValue() != null ? e.getFieldValue() : "0";
                        break;
                    case JDHSDJ:
                        this.product_price = e.getFieldValue() != null ? e.getFieldValue() : "0.00";
                        this.sales_price = e.getFieldValue() != null ? e.getFieldValue() : "0.00";
                        break;
                    default:
                        break;
                }
            }
        });
        this.subtotal = String.valueOf(Arith.mul(Double.valueOf(this.sales_price),Double.valueOf(this.quantity)));
    }

    public void QDXSDDvaluesOf(List<WorkflowRequestTableField> details) {
        if (CollectionUtils.isEmpty(details)){
            return;
        }
        details.forEach(e->{
            if (!StringUtils.isBlank(e.getFieldName()) && null != QDXSDDFieldName.valuesOf(e.getFieldName())){
                switch (QDXSDDFieldName.valuesOf(e.getFieldName())){
                    case CPBM:
                        productId(e.getFieldValue());
                        break;
                    case SL:
                        this.quantity = e.getFieldValue() != null ? e.getFieldValue() : "0";
                        break;
                    case DJ:
                        this.product_price = e.getFieldValue() != null ? e.getFieldValue() : "0.00";
                        this.sales_price = e.getFieldValue() != null ? e.getFieldValue() : "0.00";
                        break;
                    default:
                        break;
                }
            }
        });
        this.subtotal = String.valueOf(Arith.mul(Double.valueOf(this.sales_price),Double.valueOf(this.quantity)));
    }

    /**
     * 通过产品编码查询产品id
     * @param value
     */
    private void productId(String value){
        if (StringUtils.isBlank(value)){
            return;
        }
        try {
            ProductService productService = SpringBeanUtils.getBean(ProductService.class);
            List<ProductModel> productModels = productService.queryProductsByProductCode(value);
            if (!CollectionUtils.isEmpty(productModels)){
                List<ProductModel> collect = productModels.stream().filter(p -> !p.getIs_deleted()).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(collect)) {
                    this.product_id = collect.get(0).get_id();
                }
            }
        } catch (AccessTokenException ex) {
            ex.printStackTrace();
        }
    }
}

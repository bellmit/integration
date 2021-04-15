package com.qunjie.jindie.saleorder.save.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.common.annotation.Describe;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.vo.FTaxDetailSubEntity
 *
 * @author whs
 *         Date:   2020/12/8  14:49
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Describe("税务明细")
@Data
public class FTaxDetailSubEntity {

    @Describe("实体主键")
    private Long FDetailID;

    @Describe("税率名称")
    private String FTaxRateId;

    @Describe("税率%")
    private Double FTaxRate;

    @Describe("税额")
    private String FTaxAmount;

    @Describe("计入成本比例%")
    private String FCostPercent;

    @Describe("计入成本金额")
    private String FCostAmount;

    @Describe("增值税")
    private String FVAT;

    @Describe("买方代扣代缴")
    private String FBuyerWithholding;

    @Describe("卖方代扣代缴")
    private String FSellerWithholding;
}

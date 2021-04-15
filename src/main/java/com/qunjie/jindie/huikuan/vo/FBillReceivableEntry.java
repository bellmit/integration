package com.qunjie.jindie.huikuan.vo;

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.pojo.Entity1;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.vo.FbillReceivableEntry
 *
 * @author whs
 * Date:   2021/1/25  16:09
 * Description: 应收票据明细
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class FBillReceivableEntry {

    @Describe("实体主键")
    private Long FEntryID;
    @Describe("票据流水号")
    private Entity2 FBILLID;
    @Describe("票据类型")
    private String FBPBILLTYPE;
    @Describe("到期日")
    private String FBPBILLDUEDATE;
    @Describe("结算状态")
    private String FBPSETTLESTATUS;
    @Describe("可用余额")
    private String FPARLEFTAMOUNTFOR;
    @Describe("当前占用金额")
    private Double FUSEDAMOUNTFOR;
    @Describe("内部账号")
    private Entity1 FInnerAccountID_B;
    @Describe("票据组织")
    private Entity2 FTempOrgId;
    @Describe("票据号")
    private String FBPBILLNUMBER;
    @Describe("票面金额本位币")
    private Double FBILLPARAMOUNT;
    @Describe("可用余额本位币")
    private Double FPARLEFTAMOUNTSTD;
    @Describe("当前占用金额本位币")
    private Double FUSEDAMOUNTSTD;
    @Describe("票面金额")
    private String FBPBILLPARAMOUNT;
    @Describe("是否托管")
    private String FISTRUST;
}

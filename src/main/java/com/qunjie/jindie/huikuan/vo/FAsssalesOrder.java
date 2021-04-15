package com.qunjie.jindie.huikuan.vo;

import com.qunjie.common.annotation.Describe;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.vo.FasssalesOrder
 *
 * @author whs
 * Date:   2021/1/25  15:56
 * Description: 关联销售订单
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class FAsssalesOrder {

    @Describe("实体主键")
    private Long FDetailID;
    @Describe("关联单据编号")
    private String FASSBILLNO;
    @Describe("关联单据ID")
    private String FASSBILLID;
    @Describe("关联金额")
    private String FASSAMOUNTFOR;
    @Describe("预付预收已核销金额")
    private String FPREMATCHAMOUNTFOR;
}

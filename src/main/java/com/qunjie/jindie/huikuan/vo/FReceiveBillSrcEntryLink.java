package com.qunjie.jindie.huikuan.vo;

import com.qunjie.common.annotation.Describe;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.vo.FReceiveBillSrcEntryLink
 *
 * @author whs
 * Date:   2021/1/25  17:23
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class FReceiveBillSrcEntryLink {

    @Describe("实体主键")
    private Long FLinkId;
    @Describe("业务流程图")
    private String FRECEIVEBILLSRCENTRY_Link_FFlowId;
    @Describe("推进路线")
    private String FRECEIVEBILLSRCENTRY_Link_FFlowLineId;
    @Describe("转换规则")
    private String FRECEIVEBILLSRCENTRY_Link_FRuleId = "bcd48d86-aee2-4184-b3f9-79eb68bafaad";
    @Describe("源单表内码")
    private String FRECEIVEBILLSRCENTRY_Link_FSTableId;
    @Describe("源单表")
    private String FRECEIVEBILLSRCENTRY_Link_FSTableName = "T_SAL_ORDER";
    @Describe("源单内码")
    private String FRECEIVEBILLSRCENTRY_Link_FSBillId;
    @Describe("源单分录内码")
    private String FRECEIVEBILLSRCENTRY_Link_FSId;
    @Describe("原始携带量")
    private String FRECEIVEBILLSRCENTRY_Link_FREALRECAMOUNTOld;
    @Describe("修改携带量")
    private String FRECEIVEBILLSRCENTRY_Link_FREALRECAMOUNT;
    @Describe("迁移图")
    private String FRECEIVEBILLSRCENTRY_Link_FLnkTrackerId;
    @Describe("上游状态")
    private String FRECEIVEBILLSRCENTRY_Link_FLnkSState;
    @Describe("数量FLnk")
    private String FRECEIVEBILLSRCENTRY_Link_FLnkAmount;
}

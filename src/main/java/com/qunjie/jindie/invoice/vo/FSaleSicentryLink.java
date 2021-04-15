package com.qunjie.jindie.invoice.vo;

import com.qunjie.common.annotation.Describe;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.invoice.vo.FSaleSicentryLink
 *
 * @author whs
 * Date:   2021/1/19  16:42
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class FSaleSicentryLink {
    @Describe("实体主键")
    private String FLinkId;
    @Describe("业务流程图")
    private String FSALESICENTRY_Link_FFlowId;
    @Describe("推进路线")
    private String FSALESICENTRY_Link_FFlowLineId;
    @Describe("转换规则")
    private String FSALESICENTRY_Link_FRuleId = "6993aec4-bd96-43b6-83cd-5202b318a38d";
    @Describe("源单表内码")
    private String FSALESICENTRY_Link_FSTableId;
    @Describe("源单表")
    private String FSALESICENTRY_Link_FSTableName = "T_SAL_ORDERENTRY";
    @Describe("源单内码")
    private String FSALESICENTRY_Link_FSBillId;
    @Describe("源单分录内码")
    private String FSALESICENTRY_Link_FSId;
    @Describe("原始携带量")
    private String FSALESICENTRY_Link_FBASICUNITQTYOld;
    @Describe("修改携带量")
    private String FSALESICENTRY_Link_FBASICUNITQTY;
    @Describe("原始携带量")
    private String FSALESICENTRY_Link_FALLAMOUNTFOROld;
    @Describe("修改携带量")
    private String FSALESICENTRY_Link_FALLAMOUNTFOR;
    @Describe("迁移图")
    private String FSALESICENTRY_Link_FIVLnkTrackerId;
    @Describe("上游状态")
    private String FSALESICENTRY_Link_FIVLnkSState;
    @Describe("数量FIVLnk")
    private String FSALESICENTRY_Link_FIVLnkAmount;
}

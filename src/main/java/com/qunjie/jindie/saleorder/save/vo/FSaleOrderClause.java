package com.qunjie.jindie.saleorder.save.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.pojo.Entity2;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.vo.FSaleOrderClause
 *
 * @author whs
 *         Date:   2020/12/8  13:41
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
@Describe("订单条款")
public class FSaleOrderClause {

    @Describe("实体主键")
    private Long FEntryID;

    @Describe("条款编码")
    private Entity2 FClauseId;

    @Describe("条款名称")
    private String FClauseName;

    @Describe("条款类型")
    private String FClauseType;

    @Describe("条款内容")
    private String FClauseContent;

    @Describe("描述")
    private String FClauseDesc;

    public FSaleOrderClause(Long FEntryID, Entity2 FClauseId, String FClauseName, String FClauseType, String FClauseContent, String FClauseDesc) {
        this.FEntryID = FEntryID;
        this.FClauseId = FClauseId;
        this.FClauseName = FClauseName;
        this.FClauseType = FClauseType;
        this.FClauseContent = FClauseContent;
        this.FClauseDesc = FClauseDesc;
    }

    public FSaleOrderClause() {
    }
}

package com.qunjie.jindie.saleorder.save.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.saleorder.save.pojo.Entity4;
import lombok.Data;

import java.util.List;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.vo.FSalOrderTrace
 *
 * @author whs
 *         Date:   2020/12/8  15:00
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Describe("物流跟踪明细")
@Data
public class FSalOrderTrace {

    private Long FEntryID;

    @Describe("物流公司")
    private Entity4 FLogComId;

    @Describe(value = "物流单号",describe = "必填")
    private String FCarryBillNo;

    @Describe("物流状态")
    private String FTraceStatus;

    @Describe("发货时间")
    private String FDelTime;

    @Describe("寄件人手机号码")
    private String FPhoneNumber;

    @Describe("物流详细信息")
    private List<FSalOrderTraceDetail> FSalOrderTraceDetail;

    public FSalOrderTrace() {
    }

    public FSalOrderTrace(String FCarryBillNo, List<FSalOrderTraceDetail> FSalOrderTraceDetail) {
        this.FCarryBillNo = FCarryBillNo;
        this.FSalOrderTraceDetail = FSalOrderTraceDetail;
    }
}

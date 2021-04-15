package com.qunjie.jindie.saleorder.save.vo;/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.common.annotation.Describe;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.vo.FSalOrderTraceDetail
 *
 * @author whs
 *         Date:   2020/12/8  15:02
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Describe("物流详细信息")
@Data
public class FSalOrderTraceDetail {

    private Long FDetailID;

    @Describe("时间")
    private String FTraceTime;

    @Describe("物流详情")
    private String FTraceDetail;

    public FSalOrderTraceDetail() {
    }

    public FSalOrderTraceDetail(Long FDetailID, String FTraceTime, String FTraceDetail) {
        this.FDetailID = FDetailID;
        this.FTraceTime = FTraceTime;
        this.FTraceDetail = FTraceDetail;
    }
}

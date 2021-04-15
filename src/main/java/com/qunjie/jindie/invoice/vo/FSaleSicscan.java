package com.qunjie.jindie.invoice.vo;

import com.qunjie.common.annotation.Describe;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.invoice.vo.FsaleSicscan
 *
 * @author whs
 * Date:   2021/1/18  16:06
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
@Describe("扫描信息")
public class FSaleSicscan {

    @Describe("实体主键")
    private Long FEntryID;

    @Describe("发票代码")
    private String FCODESC;

    @Describe("发票号")
    private String FNUMBERSC;

    @Describe("发票金额")
    private Double FAMOUNTSC;

    @Describe("发票日期")
    private String FINVOICEDATESC;
}

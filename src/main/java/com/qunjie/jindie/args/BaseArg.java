package com.qunjie.jindie.args;

import com.qunjie.common.annotation.Describe;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.args.BaseArg
 *
 * @author whs
 * Date:   2021/1/18  14:15
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class BaseArg {


    @Describe("需要更新的字段，数组类型，格式：[key1,key2,...] （非必录）注（更新单据体字段得加上单据体key）")
    private String[] NeedUpDateFields;

    @Describe("需返回结果的字段集合，数组类型，格式：[key,entitykey.key,...]（非必录） 注（返回单据体字段格式：entitykey.key）")
    private String[] NeedReturnFields;

    @Describe("是否删除已存在的分录，布尔类型，默认true（非必录")
    private Boolean IsDeleteEntry = true;

    @Describe("表单所在的子系统内码，字符串类型（非必录）")
    private String SubSystemId;

    @Describe("是否验证所有的基础资料有效性，布尔类，默认false（非必录）")
    private Boolean IsVerifyBaseDataField ;

    @Describe("是否批量填充分录，默认true（非必录）")
    private Boolean IsEntryBatchFill;

    @Describe("是否验证标志，布尔类型，默认true（非必录）")
    private Boolean ValidateFlag;

    @Describe("是否用编码搜索基础资料，布尔类型，默认true（非必录）")
    private Boolean NumberSearch;

    @Describe("交互标志集合，字符串类型，分号分隔，格式：flag1;flag2;...（非必录） 例如（允许负库存标识：STK_InvCheckResult）")
    private String InterationFlags;
}

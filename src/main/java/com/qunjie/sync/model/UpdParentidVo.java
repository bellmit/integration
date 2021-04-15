package com.qunjie.sync.model;

import com.qunjie.common.annotation.Describe;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.sync.model.UpdParentidVo
 *
 * @author whs
 * Date:   2020/12/22  9:04
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */

@Data
public class UpdParentidVo {
    @Describe("保存后的crm中的id")
    private String crmId;
    @Describe("待转换的部门父级id")
    private Integer parentid;

    @Describe("用户账号")
    private String account;
}

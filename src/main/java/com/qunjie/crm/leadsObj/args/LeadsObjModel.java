package com.qunjie.crm.leadsObj.args;

import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.leadsObj.args.LeadsObjModel
 *
 * @author whs
 * Date:   2021/3/5  15:24
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class LeadsObjModel {

    private LeadsObjObjectData object_data;

    public LeadsObjModel(LeadsObjObjectData object_data) {
        this.object_data = object_data;
    }
}

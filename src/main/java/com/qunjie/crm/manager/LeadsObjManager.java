package com.qunjie.crm.manager;

import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.leadsObj.args.LeadsObjModel;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.LeadsObjManager
 *
 * @author whs
 * Date:   2021/3/5  17:43
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public interface LeadsObjManager {

    public CrmAddResult saveLeadsObj(LeadsObjModel leadsObjModel) throws AccessTokenException;
}

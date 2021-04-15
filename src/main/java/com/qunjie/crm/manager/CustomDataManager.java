package com.qunjie.crm.manager;

import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.args.BaseModelArg;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.manager.CustomDataManager
 *
 * @author whs
 * Date:   2021/3/10  9:32
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public interface CustomDataManager {

    public CrmAddResult saveCustomData(BaseModelArg model) throws AccessTokenException;

    public BaseResult deleteCustomData(BaseModelArg arg) throws AccessTokenException;

    public BaseResult invalidCustomData(BaseModelArg arg) throws AccessTokenException;

    public BaseResult modifyCustomData(BaseModelArg arg) throws  AccessTokenException;
}

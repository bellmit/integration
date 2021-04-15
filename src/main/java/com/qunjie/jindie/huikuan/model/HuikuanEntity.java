package com.qunjie.jindie.huikuan.model;

import com.qunjie.jindie.args.BaseArg;
import com.qunjie.jindie.huikuan.vo.FBillHead;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.huikuan.model.HuikuanEntity
 *
 * @author whs
 * Date:   2021/1/25  14:49
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class HuikuanEntity extends BaseArg {

    private FBillHead Model;

    public HuikuanEntity(FBillHead model) {
        Model = model;
    }
}

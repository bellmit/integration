package com.qunjie.crm.manager.args;

import com.qunjie.crm.beans.args.Arg;
import com.qunjie.crm.beans.args.BaseArg;
import com.qunjie.crm.utils.DefaultValues;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.saleTarget.model.ModifySaleTargetArg
 *
 * @author whs
 * Date:   2021/3/19  10:15
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class ModifyCustomArg<T> extends BaseArg {

    private boolean triggerWorkFlow = false;

    private String currentOpenUserId = DefaultValues.CURRENTOPENUSERID;

    private Object_data<T> data;

    @Data
    public static class Object_data<T> extends BaseModelArg implements Arg {
        T object_data;

        public Object_data(T object_data) {
            this.object_data = object_data;
        }
    }
}

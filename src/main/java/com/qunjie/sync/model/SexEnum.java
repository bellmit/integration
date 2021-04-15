package com.qunjie.sync.model;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.sync.model.SexEnum
 *
 * @author whs
 * Date:   2020/12/28  17:28
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public enum  SexEnum {
    M("男"),
    F("女");
    private String value;

    SexEnum(String value) {
        this.value = value;
    }

    public static String valuesOf(String value){
        for (SexEnum sexEnum : SexEnum.values()){
            if (sexEnum.value.equalsIgnoreCase(value)){
                return sexEnum.name();
            }
        }
        return M.name();
    }
}

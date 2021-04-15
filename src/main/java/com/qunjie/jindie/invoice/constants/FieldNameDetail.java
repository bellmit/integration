package com.qunjie.jindie.invoice.constants;

import com.qunjie.jindie.saleorder.save.enums.FieldName;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.invoice.constants.FieldNameDetail
 *
 * @author whs
 * Date:   2021/1/18  16:45
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public enum FieldNameDetail {

    CPBM,       //产品编码
    CPMC,       //产品名称
    GGXH,       //规格型号
    BCKPSL,     //本次开票数量
    HSDJY,      //含税单价(元)
    JSHJY,      //价税合计(元)
    BZ;         //备注

    public static FieldNameDetail valuesOf(String value) {
        for (FieldNameDetail fieldNameDetail : FieldNameDetail.values()) {
            if (fieldNameDetail.name().equalsIgnoreCase(value)){
                return fieldNameDetail;
            }
        }
        return null;
    }
}

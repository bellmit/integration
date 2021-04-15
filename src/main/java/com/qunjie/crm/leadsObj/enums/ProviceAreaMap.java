package com.qunjie.crm.leadsObj.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.leadsObj.enums.ProviceAreaMap
 *
 * @author whs
 * Date:   2021/3/5  16:57
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class ProviceAreaMap {

    /**
     * 1、华东地区（包括山东、江苏、安徽、浙江、福建、上海）；
     * 2、华南地区（包括广东、广西、海南）；
     * 3、华中地区（包括湖北、湖南、河南、江西）；
     * 4、华北地区（包括北京、天津、河北、山西、内蒙古）；
     * 5、西北地区（包括宁夏、新疆、青海、陕西、甘肃）；
     * 6、西南地区（包括四川、云南、贵州、西藏、重庆）；
     * 7、东北地区（包括辽宁、吉林、黑龙江）；
     * 8、台港澳地区（包括台湾、香港、澳门）
     */
    public static final Map<String,AreaPersonEnum> map = new HashMap(){
        {
            put("山东",AreaPersonEnum.SD);
            put("江苏",AreaPersonEnum.SD);
            put("安徽",AreaPersonEnum.SD);
            put("浙江",AreaPersonEnum.SD);
            put("福建",AreaPersonEnum.SD);
            put("上海",AreaPersonEnum.SD);
            put("广东",AreaPersonEnum.SD);
            put("广西",AreaPersonEnum.SD);
            put("海南",AreaPersonEnum.SD);
            put("湖北",AreaPersonEnum.SD);
            put("湖南",AreaPersonEnum.SD);
            put("河南",AreaPersonEnum.SD);
            put("江西",AreaPersonEnum.SD);
            put("北京",AreaPersonEnum.SD);
            put("天津",AreaPersonEnum.SD);
            put("河北",AreaPersonEnum.SD);
            put("山西",AreaPersonEnum.SD);
            put("内蒙古",AreaPersonEnum.SD);
            put("宁夏",AreaPersonEnum.SD);
            put("新疆",AreaPersonEnum.SD);
            put("青海",AreaPersonEnum.SD);
            put("陕西",AreaPersonEnum.SD);
            put("甘肃",AreaPersonEnum.SD);
            put("四川",AreaPersonEnum.SD);
            put("云南",AreaPersonEnum.SD);
            put("贵州",AreaPersonEnum.SD);
            put("西藏",AreaPersonEnum.SD);
            put("重庆",AreaPersonEnum.SD);
            put("辽宁",AreaPersonEnum.SD);
            put("吉林",AreaPersonEnum.SD);
            put("黑龙江",AreaPersonEnum.SD);
            put("台湾",AreaPersonEnum.SD);
            put("香港",AreaPersonEnum.SD);
            put("澳门",AreaPersonEnum.SD);
        }
    };
}

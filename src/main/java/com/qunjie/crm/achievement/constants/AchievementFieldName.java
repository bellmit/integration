package com.qunjie.crm.achievement.constants;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.achievement.constants.AchievementFieldName
 *
 * @author whs
 * Date:   2021/2/25  10:37
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public enum  AchievementFieldName {
    HTBHX,          //合同编号
    KHBM,           //客户编码
    HKDWMC,          //客户名称
    HZHB,          //合作伙伴
    HKJE,          //回款金额(元)
    XSYBM,          //销售员编码
    KHJL1,          //销售员姓名
    YJZB,          //业绩占比
    YJY,          //回款业绩(元)
    LXWB,          //销售类型
    SZTD,          //所属团队
    SZDQ,          //所属大区
    HKRQ;          //回款日期


    public static AchievementFieldName valuesOf(String value) {
        for (AchievementFieldName achievementFieldName : AchievementFieldName.values()) {
            if (achievementFieldName.name().equalsIgnoreCase(value)){
                return achievementFieldName;
            }
        }
        return null;
    }
}

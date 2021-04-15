package com.qunjie.crm.confirmAchievement.constants;

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
public enum ConfirmAchievementFieldName {
    XSHT,          //合同编号
    KHMC,          //客户名称
    HZHB,          //合作伙伴
    HKJE,          //回款金额(元)
    HKYJ,           //回款业绩
    GZLZB,           //工作量占比(%)
    YWF,           //业务费(元)
    ZDF,           //招待费(元)
    JYJ,           //净业绩
    GRZZYJ,           //个人最终业绩
    KHJL,          //客户经理
    XSLX,          //销售类型
    QYLX,          //签约类型
    HKSJ;          //回款日期


    public static ConfirmAchievementFieldName valuesOf(String value) {
        for (ConfirmAchievementFieldName achievementFieldName : ConfirmAchievementFieldName.values()) {
            if (achievementFieldName.name().equalsIgnoreCase(value)){
                return achievementFieldName;
            }
        }
        return null;
    }
}

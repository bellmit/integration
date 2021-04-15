package com.qunjie.crm.achievement.args;

import com.qunjie.crm.beans.args.BaseArg;
import com.qunjie.crm.utils.DefaultValues;
import lombok.Data;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.achievement.args.AchievementInvalidArg
 *
 * @author whs
 * Date:   2021/2/25  13:50
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class AchievementInvalidArg extends BaseArg {

    private String currentOpenUserId = DefaultValues.CURRENTOPENUSERID;

    private AchievementInvalidModel data;

    @Data
    public static class AchievementInvalidModel{
        private List<String> idList;
        private String dataObjectApiName = "object_n5KJ2__c";

        public AchievementInvalidModel(List<String> idList) {
            this.idList = idList;
        }
    }
}

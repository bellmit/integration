package com.qunjie.crm.attendance.args;

import com.google.common.base.MoreObjects;
import com.qunjie.crm.beans.args.BaseArg;
import lombok.Data;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.attendance.args.AttendanceArg
 *
 * @author whs
 * Date:   2021/2/20  10:30
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class AttendanceQueryArg extends BaseArg {

    private Long endTime;

    private Long startTime;

    private int pageSize = 1000;

    private int pageNumber;

    private List<String> openUserIds;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("endTime", endTime)
                .add("startTime", startTime)
                .add("openUserIds", openUserIds)
                .toString();
    }

    public AttendanceQueryArg(Long endTime, Long startTime, List<String> openUserIds) {
        this.endTime = endTime;
        this.startTime = startTime;
        this.openUserIds = openUserIds;
    }
}

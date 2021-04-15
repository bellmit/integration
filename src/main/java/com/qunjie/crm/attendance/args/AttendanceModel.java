package com.qunjie.crm.attendance.args;

import com.qunjie.mysql.model.CrmAttendance;
import lombok.Data;

import java.util.Date;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.attendance.args.AttendanceModel
 *
 * @author whs
 * Date:   2021/2/20  11:39
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class AttendanceModel {

    private String openUserId;
    private String userName;
    private String createDateStr;
    private Long checkTime;
    private Integer checkType;
    private Integer locationType;
    private Integer locationException;
    private String deviceId;
    private String checkAddress;
    private Integer deviceException;
    private Integer systemException;
    //crm外勤信息
    private Long checkinsTimeStamp;
    private String checkinsAddressDesc;

    public CrmAttendance valueOf(){
        if (this.checkTime != null) {
            return new CrmAttendance(null, this.userName, new Date(this.checkTime), this.checkAddress, this.openUserId);
        }
        else if (this.checkinsTimeStamp != null){
            return new CrmAttendance(null,this.userName,new Date(this.checkinsTimeStamp),this.checkinsAddressDesc,this.openUserId);
        }
        return new CrmAttendance();
    }
}

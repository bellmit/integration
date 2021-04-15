package com.qunjie.mysql.model;

import com.qunjie.mysql.args.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.model.CrmAttendance
 *
 * @author whs
 * Date:   2021/2/20  13:41
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class CrmAttendance extends BaseEntity {

    private Long indocno;

    private String username;

    private Date checktime;

    private String checkaddress;

    private String openuserid;

    public CrmAttendance() {
    }

    public CrmAttendance(Long indocno, String username, Date checktime, String checkaddress, String openuserid) {
        this.indocno = indocno;
        this.username = username;
        this.checktime = checktime;
        this.checkaddress = checkaddress;
        this.openuserid = openuserid;
    }
}

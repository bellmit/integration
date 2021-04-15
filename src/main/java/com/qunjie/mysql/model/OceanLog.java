package com.qunjie.mysql.model;

import com.qunjie.mysql.args.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.model.OceanLog
 *
 * @author whs
 * Date:   2021/3/5  10:14
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class OceanLog extends BaseEntity {

    private Integer indocno;

    private Date date;

    private String args;

    private String response;

    private String method;

    private String clazz;

    public OceanLog(Integer indocno, Date date, String args, String response, String method, String clazz) {
        this.indocno = indocno;
        this.date = date;
        this.args = args;
        this.response = response;
        this.method = method;
        this.clazz = clazz;
    }
}

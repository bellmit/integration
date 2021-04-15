package com.qunjie.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.mysql.model.LoggerEntity
 *
 * @author whs
 * Date:   2020/12/29  9:34
 * Description: 日志记录实体表
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class LoggerEntity {

    private Integer indocno;

    private Date date;

    private String args;

    private Integer code;

    private String response;

    private String method;

    public LoggerEntity() {
    }

    public LoggerEntity(Integer indocno, Date date, String args, Integer code, String response,String method) {
        this.indocno = indocno;
        this.date = date;
        this.args = args;
        this.code = code;
        this.response = response;
        this.method = method;
    }
}

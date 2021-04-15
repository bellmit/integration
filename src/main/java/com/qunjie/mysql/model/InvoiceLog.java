package com.qunjie.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.model.InvoiceLog
 *
 * @author whs
 * Date:   2021/1/19  9:47
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class InvoiceLog {

    private Integer indocno;

    private Date date;

    private String args;

    private String response;

    private String method;

    private String clazz;

    private String systemnm;

    public InvoiceLog() {
    }

    public InvoiceLog(Integer indocno, Date date, String args, String response, String method, String clazz, String systemnm) {
        this.indocno = indocno;
        this.date = date;
        this.args = args;
        this.response = response;
        this.method = method;
        this.clazz = clazz;
        this.systemnm = systemnm;
    }
}

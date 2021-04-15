package com.qunjie.mysql.service;

import com.qunjie.mysql.mapper.CrmInvoiceLogMapper;
import com.qunjie.mysql.model.InvoiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.service.InvoiceLogService
 *
 * @author whs
 * Date:   2021/1/19  9:54
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class CrmInvoiceLogService {

    @Autowired
    CrmInvoiceLogMapper crmInvoiceLogMapper;

    public int AddLog(InvoiceLog invoiceLog){
        return crmInvoiceLogMapper.add(invoiceLog);
    }
}

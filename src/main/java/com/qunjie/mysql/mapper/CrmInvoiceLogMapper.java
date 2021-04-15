package com.qunjie.mysql.mapper;

import com.qunjie.mysql.model.CrmCustLog;
import com.qunjie.mysql.model.InvoiceLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.mapper.FanweiInvoiceLogMapper
 *
 * @author whs
 * Date:   2021/1/19  9:49
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Repository
public interface CrmInvoiceLogMapper {

    @Insert("insert into crm_invoice_logs(indocno,date,args,response,method,clazz,systemnm)\n" +
            "        VALUES (#{condition.indocno},#{condition.date},#{condition.args},#{condition.response},#{condition.method},#{condition.clazz},#{condition.systemnm})")
    int add(@Param("condition") InvoiceLog condition);
}

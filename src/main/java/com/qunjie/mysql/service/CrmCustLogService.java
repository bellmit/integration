package com.qunjie.mysql.service;/**
 * Created by whs on 2021/1/5.
 */
import com.qunjie.mysql.mapper.CrmCustLogMapper;
import com.qunjie.mysql.model.CrmCustLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.manager.CrmCustLogService
 *
 * @author whs
 *         Date:   2021/1/5  15:10
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Service
public class CrmCustLogService {

    @Autowired
    CrmCustLogMapper crmCustLogMapper;

    public int AddLog(CrmCustLog crmCustLog){
        return crmCustLogMapper.add(crmCustLog);
    }
}

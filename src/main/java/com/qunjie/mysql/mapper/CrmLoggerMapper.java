package com.qunjie.mysql.mapper;

import com.qunjie.mysql.model.LoggerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.mysql.mapper.CrmLoggerMapper
 *
 * @author whs
 * Date:   2020/12/29  9:39
 * Description:     人资同步日志
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Repository
public interface CrmLoggerMapper {

    public int add(@Param("condition") LoggerEntity condition);
}

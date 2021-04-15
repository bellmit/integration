package com.qunjie.mysql.mapper;

import com.qunjie.mysql.model.LoggerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.oacrmbridge.mysql.mapper.CrmSaleOrderLogMapper
 *
 * @author whs
 * Date:   2021/1/14  15:16
 * Description:     销售订单同步日志
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Repository
public interface CrmSaleOrderLogMapper {

    public int add(@Param("condition") LoggerEntity condition);
}

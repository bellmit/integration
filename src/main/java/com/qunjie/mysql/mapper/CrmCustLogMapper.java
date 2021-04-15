package com.qunjie.mysql.mapper;/**
 * Created by whs on 2021/1/5.
 */

import com.qunjie.mysql.model.CrmCustLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.mapper.CrmCustLogMapper
 *
 * @author whs
 *         Date:   2021/1/5  15:02
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Repository
public interface CrmCustLogMapper {

    int add(@Param("condition") CrmCustLog condition);

}

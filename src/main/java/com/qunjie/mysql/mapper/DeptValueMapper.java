package com.qunjie.mysql.mapper;

import com.qunjie.mysql.model.DeptValue;
import com.qunjie.mysql.param.DeptValueParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.mysql.mapper.DeptValue
 *
 * @author whs
 * Date:   2020/12/21  16:26
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Repository
public interface DeptValueMapper {

    DeptValue findByCondition(@Param("condition") DeptValueParam condition);

    int insertDept(@Param("condition") DeptValue condition);

    int updDept(@Param("condition") DeptValue condition);

    void delete(@Param("deptid") Integer deptid);
}

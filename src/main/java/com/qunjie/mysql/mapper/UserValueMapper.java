package com.qunjie.mysql.mapper;

import com.qunjie.mysql.model.UserValue;
import com.qunjie.mysql.param.UserValueParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.mysql.mapper.UserValueMapper
 *
 * @author whs
 * Date:   2020/12/21  17:11
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Repository
public interface UserValueMapper {

    List<UserValue> findByCondition2(@Param("condition") UserValue condition);

    UserValue findByCondition(@Param("condition") UserValueParam condition);

    int insertUser(@Param("condition") UserValue condition);

    int updUser(@Param("condition") UserValue condition);

    int delete(@Param("condition") UserValueParam condition);
}

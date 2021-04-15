package com.qunjie.mysql;

import com.qunjie.mysql.args.BaseEntity;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.BaseMapper
 *
 * @author whs
 * Date:   2021/2/4  11:20
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public interface BaseMapper<T extends BaseEntity> {

    @InsertProvider(type = BaseSqlProvider.class,method = "insert")
    int insert(T t);

    @UpdateProvider(type = BaseSqlProvider.class,method = "update")
    int update(T t);

    @DeleteProvider(type = BaseSqlProvider.class,method = "delete")
    int delete(T t);

    @SelectProvider(type = BaseSqlProvider.class,method = "select")
    T select(T t);
}

package com.qunjie.mysql.mapper;

import com.qunjie.mysql.model.K3CloudLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.mapper.K3CloudLogMapper
 *
 * @author whs
 * Date:   2021/1/22  14:33
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Repository
public interface K3CloudLogMapper {

    @Insert("insert into jindie_k3cloud_logs(indocno,date,sformid,args,response,method,clazz,systemnm)\n" +
            "        VALUES (#{condition.indocno},#{condition.date},#{condition.sformid},#{condition.args},#{condition.response},#{condition.method},#{condition.clazz},#{condition.systemnm})")
    int add(@Param("condition") K3CloudLog condition);
}

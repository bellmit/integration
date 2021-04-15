package com.qunjie.mysql.mapper;

import com.qunjie.mysql.BaseMapper;
import com.qunjie.mysql.model.CrmAttendance;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.mapper.CrmAttendanceMapper
 *
 * @author whs
 * Date:   2021/2/20  13:41
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Repository
public interface CrmAttendanceMapper extends BaseMapper<CrmAttendance> {

    @Delete("delete from crm_attendance where checktime < #{time}")
    int deleteAttendance(Date time);
}

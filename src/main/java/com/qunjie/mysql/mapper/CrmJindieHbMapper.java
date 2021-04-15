package com.qunjie.mysql.mapper;

import com.qunjie.mysql.BaseMapper;
import com.qunjie.mysql.model.CrmJindieHb;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.mapper.CrmJindieHbMapper
 *
 * @author whs
 * Date:   2021/2/5  10:25
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Repository
public interface CrmJindieHbMapper extends BaseMapper<CrmJindieHb> {

    @Select("select * from crm_jindie_hb where crm_hb_id = #{crm_hb_id}")
    CrmJindieHb selectByCrmHbId(String crm_hb_id);
}

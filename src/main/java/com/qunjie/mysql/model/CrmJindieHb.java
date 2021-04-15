package com.qunjie.mysql.model;

import com.qunjie.mysql.args.BaseEntity;
import lombok.Data;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.model.CrmJindieHb
 *
 * @author whs
 * Date:   2021/2/5  10:23
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Data
public class CrmJindieHb extends BaseEntity {

    private Integer indocno;

    private String crmHbId;

    private String jindieHbId;

    private String hbName;
}

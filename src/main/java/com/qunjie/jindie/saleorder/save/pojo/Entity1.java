package com.qunjie.jindie.saleorder.save.pojo;
/**
 * Created by whs on 2020/12/8.
 */

import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.pojo.Entity
 *
 * @author whs
 *         Date:   2020/12/8  15:15
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Data
public class Entity1 {

    private String FNUMBER;

    public Entity1() {
    }

    public Entity1(String FNUMBER) {
        this.FNUMBER = FNUMBER;
    }
}

package com.qunjie.jindie.saleorder.save.model;
/**
 * Created by whs on 2020/12/8.
 */

import com.qunjie.common.annotation.Describe;
import com.qunjie.jindie.args.BaseArg;
import com.qunjie.jindie.saleorder.save.vo.FBillHead;
import lombok.Data;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.save.model.SaleOrderEntity
 *
 * @author whs
 *         Date:   2020/12/8  16:13
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Describe("销售订单保存(JSON格式数据)")
@Data
public class SaleOrderEntity extends BaseArg {

    @Describe("表单数据包，JSON类型（必录）")
    private FBillHead Model;

    public SaleOrderEntity(FBillHead model) {
        Model = model;
    }
}

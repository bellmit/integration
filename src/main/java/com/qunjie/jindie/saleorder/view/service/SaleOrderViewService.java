package com.qunjie.jindie.saleorder.view.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.jindie.saleorder.view.model.ViewEntity;
import kingdee.bos.webapi.client.K3CloudApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.saleorder.view.SaleOrderView
 *
 * @author whs
 * Date:   2021/1/25  16:57
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class SaleOrderViewService {

    public JSONObject view (K3CloudApiClient client , String htbh) throws Exception {
        ViewEntity viewEntity = new ViewEntity();
        viewEntity.setNumber(htbh);
        viewEntity.setCreateOrgId("0");
        String view = client.view("SAL_SaleOrder", new Gson().toJson(viewEntity));
//        log.info("view========================="+view);
        JSONObject jsonObject = JSONObject.parseObject(view);
        return jsonObject;
    }
}

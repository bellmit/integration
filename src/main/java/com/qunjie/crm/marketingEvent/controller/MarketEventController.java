package com.qunjie.crm.marketingEvent.controller;

import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.marketingEvent.model.MarketingEventModel;
import com.qunjie.crm.marketingEvent.service.MarketingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.marketingEvent.controller.MarketEventController
 *
 * @author whs
 * Date:   2021/1/26  15:04
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/marketEvent")
public class MarketEventController {
    @Autowired
    MarketingEventService marketingEventService;
    @GetMapping("/query")
    public List<MarketingEventModel> query(String _id) throws AccessTokenException {
       return marketingEventService.query(_id);
    }
}

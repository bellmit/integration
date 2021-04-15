package com.qunjie.crm.saleTarget.controller;

import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.saleTarget.event.SaleTargetEvent;
import com.qunjie.crm.saleTarget.model.SaleTargetModel;
import com.qunjie.crm.saleTarget.service.SaleTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.saleTarget.controller.SaleTargetController
 *
 * @author whs
 * Date:   2021/3/19  11:01
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/crm/saleTarget")
public class SaleTargetController {

    @Autowired
    SaleTargetService saleTargetService;

    @Autowired
    ApplicationContext applicationContext;
    @RequestMapping("/modify")
    public BaseResult modifyCustom(@RequestBody Map<String,Object> map) throws AccessTokenException {
        return saleTargetService.modifySaleTarget(map);
    }

    @RequestMapping("/queryGR")
    public SaleTargetModel queryGR(@RequestParam("year")String year,@RequestParam("month")String month,@RequestParam("sname")String sname) throws AccessTokenException {
        return saleTargetService.querySaleTargetGR(year,month,sname);
    }

    @RequestMapping("/sendMsg")
    public int sendMsg(){
        SaleTargetModel saleTargetModel = new SaleTargetModel();
        saleTargetModel.set_id("6052fe4487793500016d8180");
        saleTargetModel.setField_762N2__c(444.0);
        applicationContext.publishEvent(new SaleTargetEvent(this,saleTargetModel,"2021","3","魏峰"));
        System.out.println("===时间："+new Date().getTime());
        return 1;
    }
}

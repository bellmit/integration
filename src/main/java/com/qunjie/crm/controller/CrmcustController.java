package com.qunjie.crm.controller;/**
 * Created by whs on 2020/12/25.
 */

import com.google.gson.Gson;
import com.qunjie.common.util.MapToBean;
import com.qunjie.fanwei.crmcust.service.OACrmcustService;
import com.qunjie.jindie.crmcust.common.CrmCust;
import com.qunjie.jindie.crmcust.common.CrmCustHB;
import com.qunjie.jindie.crmcust.service.CrmcustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.crm.controller.CrmcustController
 *
 * @author whs
 *         Date:   2020/12/25  16:35
 *         Description: crm 客户做添加/修改/删除操作同步数据---金蝶+泛微
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/crmcust")
public class CrmcustController {

    private ExecutorService executor = new java.util.concurrent.ThreadPoolExecutor(0,
            10, 6L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(),
            new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private CrmcustService crmcustService;

    @Autowired
    private OACrmcustService oaCrmcustService;

    @PostMapping("/add")
    public void add(@RequestBody Map<String,Object> map) throws Exception {
        String s = new Gson().toJson(map);
        System.out.println("s======"+s);
        CrmCust crmCust = (CrmCust) MapToBean.mapToObject(map, CrmCust.class);
        System.out.println("=====================add===================================");
        //推金蝶
        executor.execute(()->{
            try {
                crmcustService.crmcustSave(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //推oa
        executor.execute(()->{
            try {
                oaCrmcustService.crmcustAdd(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @PostMapping("/addHb")
    public void addHb(@RequestBody Map<String,Object> map) throws Exception {
        String s = new Gson().toJson(map);
        System.out.println("s======"+s);
        CrmCustHB crmCust = (CrmCustHB) MapToBean.mapToObject(map, CrmCustHB.class);
        System.out.println("=====================addHb===================================");
        //推金蝶
        executor.execute(()->{
            try {
                crmcustService.crmcustSave(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //推oa
        executor.execute(()->{
            try {
                oaCrmcustService.crmcustAdd(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @PostMapping("update")
    public void update(@RequestBody CrmCust crmCust){
        System.out.println("=====================update===================================");
        executor.execute(()->{
            try {
                crmcustService.crmcustUpd(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @PostMapping("updateHb")
    public void updateHb(@RequestBody CrmCustHB crmCust){
        System.out.println("=====================updateHb===================================");
        executor.execute(()->{
            try {
                crmcustService.crmcustUpd(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @PostMapping("forbid")
    public void forbid(@RequestBody CrmCust crmCust){
        System.out.println("=====================delete===================================");
        executor.execute(()->{
            try {
                crmcustService.crmcustForbid(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @PostMapping("forbidHb")
    public void forbidHb(@RequestBody CrmCustHB crmCust){
        System.out.println("=====================delete===================================");
        executor.execute(()->{
            try {
                crmcustService.crmcustForbid(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @PostMapping("test")
    public String test(@RequestBody Map<String,Object> map) throws Exception {
        String s = new Gson().toJson(map);
        System.out.println("s======"+s);
        CrmCustHB crmCust = (CrmCustHB) MapToBean.mapToObject(map, CrmCustHB.class);
        System.out.println("=====================addHb===================================");
        //推金蝶
        executor.execute(()->{
            try {
                crmcustService.crmcustSave(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //推oa
        executor.execute(()->{
            try {
                oaCrmcustService.crmcustAdd(crmCust);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return s;
    }
}

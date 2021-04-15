package com.qunjie.crm.saleTarget.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.event.SendEmailEvent;
import com.qunjie.crm.beans.results.BaseResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.manager.impl.SaleTargetManagerImpl;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import com.qunjie.crm.saleTarget.model.SaleTargetModel;
import com.qunjie.crm.manager.args.ModifyCustomArg;
import com.qunjie.mysql.service.DeptValueService;
import com.qunjie.mysql.service.UserValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.saleTarget.service.SaleTargetService
 *
 * @author whs
 * Date:   2021/3/18  16:04
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class SaleTargetService {

    @Autowired
    CrmQueryService crmQueryService;
    @Autowired
    UserValueService userValueService;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    SaleTargetManagerImpl saleTargetManagerImpl;
    @Autowired
    DeptValueService deptValueService;

    public static final String APINAME = "object_11Tz8__c";

    public static final Map<String,String> field_mm2gw__c_map = new ConcurrentHashMap(){{
        put("个人","option1");
        put("团队","7m7B1Vnl4");
        put("大区","A5JdgelBv");
    }};
        //年份
        public static final Map<String,String> field_J186E__c_map = new ConcurrentHashMap(){{
            put("2021","option1");
            put("2022","wrqlsS2v4");
            put("2023","Br1LBlqDp");
            put("2024","k7a19is35");
            put("2025","sW1cnLLlO");
        }};
        //月份
        public static final Map<String,String> field_c2id0__c_map = new ConcurrentHashMap(){{
            put("1","option1");
            put("2","6dDhr2Kza");
            put("3","wI1GpC52s");
            put("4","2hzR6uvcJ");
            put("5","G44agAvo5");
            put("6","5qLSsu1m6");
            put("7","aLa6638St");
            put("8","om8F2PDut");
            put("9","i2koG72h7");
            put("10","q0Paf2mf5");
            put("11","793eOj5KJ");
            put("12","fQS21xrxo");
        }};

    /**
     * 按个人查找销售业绩
     * @param year
     * @param month
     * @param sname
     * @return
     * @throws AccessTokenException
     */
    public SaleTargetModel querySaleTargetGR(String year,String month,String sname) throws AccessTokenException {
        String openUserId = userValueService.getCrmOpenUserIdByNm(sname);
        Map<String,String> map = new HashMap<>();
        map.put("field_J186E__c",field_J186E__c_map.get(year));
        map.put("field_c2id0__c",field_c2id0__c_map.get(month));
        map.put("is_deleted","false");
        map.put("field_mm2gw__c","option1");
        map.put("owner",openUserId);
        return query(map,year,month,sname);
    }

    /**
     * 按团队查找销售业绩
     * @param year
     * @param month
     * @param deptnm
     * @return
     * @throws AccessTokenException
     */
    public SaleTargetModel querySaleTargetArea(String year,String month,String deptnm) throws AccessTokenException {
        Integer deptIdByDeptNm = deptValueService.queryCrmDeptIdByDeptNm(deptnm);
        Map<String,String> map = new HashMap<>();
        map.put("field_J186E__c",field_J186E__c_map.get(year));
        map.put("field_c2id0__c",field_c2id0__c_map.get(month));
        map.put("is_deleted","false");
        map.put("field_mm2gw__c","A5JdgelBv");
        map.put("data_own_department",String.valueOf(deptIdByDeptNm));
        return query(map,year,month,deptnm);
    }

    /**
     * 按大区查找销售业绩
     * @param year
     * @param month
     * @param deptnm
     * @return
     * @throws AccessTokenException
     */
    public SaleTargetModel querySaleTargetTeam(String year,String month,String deptnm) throws AccessTokenException {
        Integer deptIdByDeptNm = deptValueService.queryCrmDeptIdByDeptNm(deptnm);
        Map<String,String> map = new HashMap<>();
        map.put("field_J186E__c",field_J186E__c_map.get(year));
        map.put("field_c2id0__c",field_c2id0__c_map.get(month));
        map.put("is_deleted","false");
        map.put("field_mm2gw__c","7m7B1Vnl4");
        map.put("data_own_department",String.valueOf(deptIdByDeptNm));
        return query(map,year,month,deptnm);
    }

    private SaleTargetModel query(Map<String,String> map,String year,String month,String sname) throws AccessTokenException {
        QueryResult queryResult = crmQueryService.QueryCustomBy(map, APINAME);
        if (queryResult != null && queryResult.getData() != null){
            JSONArray dataList = queryResult.getData().getJSONArray("dataList");
            if (dataList == null || dataList.size() == 0){
                String message= "crm系统中--"+year+"年"+month+"月"+sname+"销售目标管理少于一条";
                log.error(message);
                applicationContext.publishEvent(new SendEmailEvent(this,message, DefaultEmailAddress.SENDTODev,"销售目标管理"));
            }else if (dataList.size() > 1){
                String message= "crm系统中--"+year+"年"+month+"月"+sname+"销售目标管理大于一条";
                log.error(message);
                applicationContext.publishEvent(new SendEmailEvent(this,message, DefaultEmailAddress.SENDTODev,"销售目标管理"));
            }else {
                JSONObject jsonObject = dataList.getJSONObject(0);
                return JSON.parseObject(jsonObject.toJSONString(), SaleTargetModel.class);
            }
        }
        return null;
    }

    public BaseResult modifySaleTarget(Map<String,Object> map) throws AccessTokenException{
        SaleTargetModel saleTargetModel = JSON.parseObject(new Gson().toJson(map), SaleTargetModel.class);
        saleTargetModel.setDataObjectApiName(APINAME);
        ModifyCustomArg.Object_data<SaleTargetModel> model = new ModifyCustomArg.Object_data(saleTargetModel);
        BaseResult baseResult = saleTargetManagerImpl.modifyCustomData(model);
        return baseResult;
    }

    public BaseResult modifySaleTarget(SaleTargetModel saleTargetModel) throws AccessTokenException{
        saleTargetModel.setDataObjectApiName(APINAME);
        ModifyCustomArg.Object_data<SaleTargetModel> model = new ModifyCustomArg.Object_data(saleTargetModel);
        BaseResult baseResult = saleTargetManagerImpl.modifyCustomData(model);
        return baseResult;
    }

}

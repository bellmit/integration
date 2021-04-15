package com.qunjie.jindie.crmcust.service;/**
 * Created by whs on 2021/1/5.
 */

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qunjie.common.response.ApiResult;
import com.qunjie.common.util.SpringBeanUtils;
import com.qunjie.jindie.crmcust.common.CrmCust;
import com.qunjie.jindie.crmcust.common.CrmCustHB;
import com.qunjie.jindie.crmcust.common.model.CrmCustEntity;
import com.qunjie.jindie.crmcust.common.model.CrmCustModel;
import com.qunjie.jindie.util.JindieHelperUtil;
import com.qunjie.mysql.mapper.CrmJindieHbMapper;
import com.qunjie.mysql.model.CrmJindieHb;
import kingdee.bos.webapi.client.K3CloudApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.crmcust.manager.CrmcustService
 *
 * @author whs
 *         Date:   2021/1/5  9:29
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class CrmcustService {

    @Autowired
    private CrmJindieHbMapper crmJindieHbMapper;

    @Value("${k3cloud.url}")
    private String K3CloudURL ;
    @Value("${k3cloud.dbId}")
    private String dbId ;
    @Value("${k3cloud.uid}")
    private String uid ;
    @Value("${k3cloud.pwd}")
    private String pwd;
    static int lang = 2052;
    static String sFormId = "BD_Customer";
    static String FORBID = "Forbid";

    public String crmcustSave(CrmCust crmCust) throws Exception {
        log.info("=========================crm客户推送金蝶===============add=====================start");
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if (result){
            CrmCustModel crmCustModel = CrmCustModel.valuesOf(crmCust);
            CrmCustEntity crmCustEntity = new CrmCustEntity(crmCustModel);
            String s = new Gson().toJson(crmCustEntity);
            String save = client.save(sFormId, s);
            log.info("crm客户推送金蝶返回值=========="+save);
            ApiResult success = ApiResult.isSuccess(save);
            ApiResult response = JindieHelperUtil.commitAndAudit(success, client, sFormId);
            log.info("==================================================="+response.toString());
            return response.toString();
        }
        return new ApiResult(0,"ERROR",null).toString();
    }

    public String crmcustSave(CrmCustHB crmCustHB) throws Exception {
        log.info("=========================crm合作伙伴推送金蝶===============add=====================start");
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if (result){
            CrmCustModel crmCustModel = CrmCustModel.valuesOf(crmCustHB);
            CrmCustEntity crmCustEntity = new CrmCustEntity(crmCustModel);
            String s = new Gson().toJson(crmCustEntity);
            String save = client.save(sFormId, s);
            log.info("crm合作伙伴推送金蝶返回值=========="+save);
            ApiResult success = ApiResult.isSuccess(save);
            ApiResult response = JindieHelperUtil.commitAndAudit(success, client, sFormId);
            log.info("==================================================="+response.toString());
            //金蝶保存成功，保留映射关系
            if (response.getCode() == 1){
                CrmJindieHb crmJindieHb = new CrmJindieHb();
                String crm_id = crmCustHB.get_id();
                crmJindieHb.setCrmHbId(crm_id);
                crmJindieHb.setJindieHbId(success.getMessage());
                crmJindieHb.setHbName(crmCustHB.getName());
                CrmJindieHb selectByCrmHbId = crmJindieHbMapper.selectByCrmHbId(crm_id);
                if (selectByCrmHbId == null){
                    crmJindieHbMapper.insert(crmJindieHb);
                }else {
                    crmJindieHb.setIndocno(selectByCrmHbId.getIndocno());
                    crmJindieHbMapper.update(crmJindieHb);
                }
            }
            return response.toString();
        }
        return new ApiResult(0,"ERROR",null).toString();
    }

    public String crmcustUpd(CrmCust crmCust) throws Exception {
        log.info("=========================crm客户修改推送金蝶=============update=======================start");
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if (result){
            String[] numbers = {crmCust.getAccount_no()};
            JindieHelperUtil.UnAudit(numbers,client,sFormId);

            CrmCustModel crmCustModel = CrmCustModel.valuesOf(crmCust);
            Integer id = this.View(crmCust.getAccount_no(), client);
            CrmCustEntity crmCustEntity ;
            if (id != 0){
                crmCustModel.setFCUSTID(id);
                crmCustEntity = new CrmCustEntity(crmCustModel);
                crmCustEntity.setNeedUpDateFields(crmCustModel.getField());
            }else {
                crmCustEntity = new CrmCustEntity(crmCustModel);
            }
            String s = new Gson().toJson(crmCustEntity);
            String save = client.save(sFormId, s);
            log.info("crm客户推送金蝶=========="+save);
            ApiResult success = ApiResult.isSuccess(save);
            ApiResult response = JindieHelperUtil.commitAndAudit(success, client, sFormId);
            log.info("====================="+response.toString());
            return response.toString();
        }
        return new ApiResult(0,"ERROR",null).toString();
    }

    public String crmcustUpd(CrmCustHB crmCust) throws Exception {
        log.info("=========================crm合作伙伴修改推送金蝶=============update=======================start");
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if (result){
            String id1 = crmCust.get_id();
            CrmJindieHb crmJindieHb = crmJindieHbMapper.selectByCrmHbId(id1);
            if (crmJindieHb != null){
                Integer integer = ViewByid(crmJindieHb.getJindieHbId(),client);
                String save;
                if (integer != null && integer != 0){
                    log.info("=================金蝶中存在该条数据，则反审核后修改，再审核");
                    JindieHelperUtil.UnAudit(crmJindieHb.getJindieHbId(),client,sFormId);
                    CrmCustModel crmCustModel = CrmCustModel.valuesOf(crmCust);
                    crmCustModel.setFCUSTID(Integer.valueOf(crmJindieHb.getJindieHbId()));
                    CrmCustEntity crmCustEntity = new CrmCustEntity(crmCustModel);
                    crmCustEntity.setNeedUpDateFields(crmCustModel.getField());
                    String s = new Gson().toJson(crmCustEntity);
                    save = client.save(sFormId, s);
                }else {
                    log.info("=================金蝶中不存在该条数据，则做添加操作");
                    CrmCustModel crmCustModel = CrmCustModel.valuesOf(crmCust);
                    CrmCustEntity crmCustEntity = new CrmCustEntity(crmCustModel);
                    String s = new Gson().toJson(crmCustEntity);
                    save = client.save(sFormId, s);
                }
                log.info("crm合作伙伴推送金蝶=========="+save);
                ApiResult success = ApiResult.isSuccess(save);
                ApiResult response = JindieHelperUtil.commitAndAudit(success, client, sFormId);
                log.info("====================="+response.toString());
                return response.toString();
            }else {
             log.info("=======================映射表中无该条数据映射，id1= " + id1);
            }
        }
        return new ApiResult(0,"ERROR",null).toString();
    }

    public String crmcustForbid(CrmCust crmCust) throws Exception {
        log.info("=========================crm客户禁用推送金蝶=============forbid=======================start");
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if (result){
            String[] numbers = {crmCust.getAccount_no()};
            JindieHelperUtil.UnAudit(numbers,client,sFormId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CreateOrgId",0);
            jsonObject.put("Numbers",numbers);
            String s = new Gson().toJson(jsonObject);
            String response = client.delete(sFormId, s);
            log.info("===========金蝶客户删除=========="+response);
            return response;
        }
        return new ApiResult(0,"ERROR",null).toString();
    }

    public String crmcustForbid(CrmCustHB crmCust) throws Exception {
        log.info("=========================crm客户禁用推送金蝶=============forbid=======================start");
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if (result){
            CrmJindieHb crmJindieHb = crmJindieHbMapper.selectByCrmHbId(crmCust.get_id());
            if (crmJindieHb != null){
                JindieHelperUtil.UnAudit(crmJindieHb.getJindieHbId(),client,sFormId);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("CreateOrgId",0);
                jsonObject.put("Ids",crmJindieHb.getJindieHbId());
                String s = new Gson().toJson(jsonObject);
                String response = client.delete(sFormId, s);
                crmJindieHbMapper.delete(crmJindieHb);
                log.info("===========金蝶客户删除=========="+response);
                return response;
            }
        }
        return new ApiResult(0,"ERROR",null).toString();
    }

    private Integer View(String Number, K3CloudApiClient client) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CreateOrgId",0);
        jsonObject.put("Number",Number);
        String view = client.view(sFormId, new Gson().toJson(jsonObject));
        JSONObject jsonObject1 = JSONObject.parseObject(view);
        Integer id = 0;
        try{
            Object o = jsonObject1.getJSONObject("Result").getJSONObject("Result").get("Id");
            id = (Integer)o;
        }catch (Exception e){
            log.info("待修改的数据不存在,编号为"+Number);
        }
        return id;
    }

    private Integer ViewByid(String jjindie_id, K3CloudApiClient client) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CreateOrgId",0);
        jsonObject.put("Id",jjindie_id);
        String view = client.view(sFormId, new Gson().toJson(jsonObject));
        JSONObject jsonObject1 = JSONObject.parseObject(view);
        Integer id = 0;
        try{
            Object o = jsonObject1.getJSONObject("Result").getJSONObject("Result").get("Id");
            id = (Integer)o;
        }catch (Exception e){
            log.info("id="+jjindie_id+"===============的合作伙伴不存在");
        }
        return id;
    }
}

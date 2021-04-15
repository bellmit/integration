package com.qunjie.crm.leadsObj.service;

import com.alibaba.fastjson.JSONObject;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.event.SendEmailEvent;
import com.qunjie.common.email.service.EmailServiceHelper;
import com.qunjie.crm.beans.results.CrmAddResult;
import com.qunjie.crm.exception.AccessTokenException;
import com.qunjie.crm.leadsObj.args.LeadsObjModel;
import com.qunjie.crm.leadsObj.args.LeadsObjObjectData;
import com.qunjie.crm.manager.impl.LeadsObjManagerImpl;
import com.qunjie.crm.query.args.QueryFilterField;
import com.qunjie.crm.query.results.QueryResult;
import com.qunjie.crm.query.service.CrmQueryService;
import com.qunjie.ocean.clue.model.OceanClueModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.crm.leadsObj.service.LeadsObjService
 *
 * @author whs
 * Date:   2021/3/5  17:37
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Slf4j
@Service
public class LeadsObjService {
    @Autowired
    LeadsObjManagerImpl leadsObjManager;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    CrmQueryService crmQueryService;

    @Autowired
    EmailServiceHelper emailServiceHelper;
    public int leadsObjAdd(List<OceanClueModel> list){
        AtomicBoolean flag = new AtomicBoolean(true);
        if (!CollectionUtils.isEmpty(list)){
            for (OceanClueModel e : list){
                if (StringUtils.isBlank(e.getRemark_dict().get("您的公司"))){
                    e.setLocation(e.getLocation()+"----(该线索公司名称为空，不录入)");
                    continue;
                }
                //该条线索的手机号已存在，不录入
                try {
                    JSONObject jsonObject = queryLeadsObj(e.getTelephone());
                    if (jsonObject != null && jsonObject.getJSONArray("dataList").size() > 0){
                        e.setLocation(e.getLocation()+"----(该线索手机号已存在，不录入)");
                        continue;
                    }
                } catch (AccessTokenException ex) {
                    ex.printStackTrace();
                }
                LeadsObjObjectData objectData = new LeadsObjObjectData();
                try {
                    objectData.valueOf(e);
                    LeadsObjModel leadsObjModel = new LeadsObjModel(objectData);
                    CrmAddResult crmAddResult = leadsObjManager.saveLeadsObj(leadsObjModel);
                    if (crmAddResult.getErrorCode() != 0){
                        flag.set(false);
                        emailServiceHelper.sendSimpleMail(DefaultEmailAddress.SENDTODev,"头条销售线索推送crm失败",
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"---\n---"+e+"-------\n"+crmAddResult);
                    }
                } catch (AccessTokenException ex) {
                    log.info("==============线索插入失败=============");
                    ex.printStackTrace();
                }
            }
        }
        if (flag.get()){
            String collect = list.stream().map(e -> (e.getName() + "--" + e.getTelephone()+ "--" + e.getRemark_dict().get("您的公司")+"--"+e.getLocation())).collect(Collectors.joining(";\n"));
            applicationContext.publishEvent(new SendEmailEvent(this,"时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n销售线索推送crm成功！明细如下：\n"+collect,
                    DefaultEmailAddress.SENDTOOCEAN,"头条销售线索共"+list.size()+"条，推送crm"));
        }
        return 1;
    }

    public JSONObject queryLeadsObj(String tel) throws AccessTokenException {
        QueryFilterField queryFilterField = new QueryFilterField();
        queryFilterField.setOperator("EQ");
        queryFilterField.setField_name("mobile");
        queryFilterField.setField_values(Arrays.asList(tel));
        QueryResult leadsObj = crmQueryService.query("LeadsObj", Arrays.asList(queryFilterField));
        return leadsObj.getData();
    }
}

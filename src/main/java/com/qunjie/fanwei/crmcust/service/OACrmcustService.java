package com.qunjie.fanwei.crmcust.service;/**
 * Created by whs on 2021/1/8.
 */

import com.qunjie.common.util.CxfInvokeHelper;
import com.qunjie.jindie.crmcust.common.CrmCust;
import com.qunjie.jindie.crmcust.common.CrmCustHB;
import com.qunjie.mysql.service.UserValueService;
import com.qunjie.mysql.model.UserValue;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.impl.util.json.XML;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.fanwei.crmcust.manager.OACrmcustService
 *
 * @author whs
 *         Date:   2021/1/8  10:25
 *         Description: 客户推送泛微
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class OACrmcustService {

    @Autowired
    private UserValueService userValueService;

    public static final String WSDLURL = "http://oa.qunje.com:88/services/CrmService?wsdl";
    public static final String METHODNAME = "customerAdd";

    public String buildXml(CrmCust crmCust,String username){
        return  "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<ROOT> \n" +
                "  <customer> \n" +
                "    <item> \n" +
                "      <fieldname>name</fieldname>  \n" +
                "      <fieldvalue>"+crmCust.getName()+"</fieldvalue> \n" +
                "    </item>  \n" +
                "    <item> \n" +
                "      <fieldname>manager</fieldname>  \n" +
                "      <fieldvalue>"+username+"</fieldvalue> \n" +
                "    </item> \n" +
                "    <item> \n" +
                "      <fieldname>mobilephone</fieldname>  \n" +
                "      <fieldvalue>"+crmCust.getTel()+"</fieldvalue> \n" +
                "    </item> \n" +
                "    <item> \n" +
                "      <fieldname>address1</fieldname>  \n" +
                "      <fieldvalue>"+crmCust.getAddress()+"</fieldvalue> \n" +
                "    </item>  \n" +
//                "    <item> \n" +
//                "      <fieldname>description</fieldname>  \n" +
//                "      <fieldvalue>"+crmCust.getRemark()+"</fieldvalue> \n" +
//                "    </item>  \n" +
                "  </customer>\n" +
                "</ROOT>";
    }

    public JSONObject crmcustAdd(CrmCust crmCust){
        log.info("===================crm客户推送泛微====================================start");
        String managerName = "";
        String userid = "7";
        Map<String, Object> userDetail = crmCust.getUserDetail();
        Map<String, Object> managerDetail = crmCust.getManagerDetail();
        if (null != managerDetail){
            //因name属性crm和oa中可能不一样(相同的name，crm中多个序列号)，而crm中full_name和oa中name是一样的
            managerName = (String)managerDetail.get("full_name");
        }
        if (null != userDetail){
            String username = (String)userDetail.get("name");
            UserValue userValue = new UserValue();
            userValue.setMobile((String) userDetail.get("phone"));
            List<UserValue> userValues = userValueService.findByCondition2(userValue);
            if (!CollectionUtils.isEmpty(userValues)){
                UserValue userValue1 = userValues.get(0);
                userid = userValue1.getUserid().toString();
            }else {
                UserValue uV = new UserValue();
                uV.setUsernm(username);
                List<UserValue> userValueList = userValueService.findByCondition2(uV);
                if (!CollectionUtils.isEmpty(userValueList)){
                    userid = userValueList.get(0).getUserid().toString();
                }
            }
        }
        String xml = buildXml(crmCust,managerName);
        String invoke = CxfInvokeHelper.invoke(WSDLURL, METHODNAME, xml, userid);
        JSONObject jsonObject ;
        if (StringUtils.isNotEmpty(invoke)) {
            jsonObject = XML.toJSONObject(invoke).getJSONObject("ROOT").getJSONObject("return");
            return jsonObject;
        }else {
            jsonObject = new JSONObject();
            jsonObject.put("returnnode","0");
            jsonObject.put("returnmessage","fault");
        }
        log.info("推送泛微response = "+jsonObject.toString());
        return jsonObject;
    }

    public JSONObject crmcustAdd(CrmCustHB crmCust){
        log.info("===================crm合作伙伴推送泛微====================================start");
        String managerName = "";
        String userid = "7";
        Map<String, Object> userDetail = crmCust.getUserDetail();
        Map<String, Object> managerDetail = crmCust.getManagerDetail();
        if (null != managerDetail){
            //因name属性crm和oa中可能不一样(相同的name，crm中多个序列号)，而crm中full_name和oa中name是一样的
            managerName = (String)managerDetail.get("full_name");
        }
        if (null != userDetail){
            String username = (String)userDetail.get("name");
            UserValue userValue = new UserValue();
            userValue.setMobile((String) userDetail.get("phone"));
            List<UserValue> userValues = userValueService.findByCondition2(userValue);
            if (!CollectionUtils.isEmpty(userValues)){
                UserValue userValue1 = userValues.get(0);
                userid = userValue1.getUserid().toString();
            }else {
                UserValue uV = new UserValue();
                uV.setUsernm(username);
                List<UserValue> userValueList = userValueService.findByCondition2(uV);
                if (!CollectionUtils.isEmpty(userValueList)){
                    userid = userValueList.get(0).getUserid().toString();
                }
            }
        }
        String xml = buildXml(transferCrmcust(crmCust),managerName);
        String invoke = CxfInvokeHelper.invoke(WSDLURL, METHODNAME, xml, userid);
        JSONObject jsonObject ;
        if (StringUtils.isNotEmpty(invoke)) {
            jsonObject = XML.toJSONObject(invoke).getJSONObject("ROOT").getJSONObject("return");
            return jsonObject;
        }else {
            jsonObject = new JSONObject();
            jsonObject.put("returnnode","0");
            jsonObject.put("returnmessage","fault");
        }
        log.info("推送泛微response = "+jsonObject.toString());
        return jsonObject;
    }

    private CrmCust transferCrmcust(CrmCustHB crmCustHB){
        CrmCust crmCust = new CrmCust();
        crmCust.setName(crmCustHB.getName());
        crmCust.setTel(crmCustHB.getTel());
        crmCust.setAddress(crmCustHB.getAddress());
        return crmCust;
    }
}

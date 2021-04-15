package com.qunjie.axis.controller;

import com.qunjie.axis.service.impl.OAServiceImpl;
import com.qunjie.axis.utils.CallWebServiceUtil;
import com.qunjie.common.response.ApiResult;
import com.qunjie.common.util.SpringBeanUtils;
import kingdee.bos.webapi.client.K3CloudApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AxisTestController {

    String url = "http://localhost:8072/services/oaService?wsdl";
    String targetNamespace = "http://impl.service.axis.qunjie.com/";
    String methodName = "SaveSaleOrder";
    String methodName2 = "DeleteSaleOrder";
    String SaveInvoice = "SaveInvoice";

    static String K3CloudURL = "http://47.100.203.167/K3Cloud/";
    static String dbId = "5f46250a7ad252";
    static String pwd = "999999999";
    static String uid = "Administrator";
    static int lang = 2052;

    @GetMapping("test")
    public String test(String requestid) {
        List<Map<String,String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("requestid", requestid);
        list.add(map);
        String s = CallWebServiceUtil.sendWsdl(url, targetNamespace, methodName, list, null);
        return s;
    }

    @GetMapping("test2")
    public String test2(String requestid) {
        List<Map<String,String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("requestid", requestid);
        list.add(map);
        String s = CallWebServiceUtil.sendWsdl(url, targetNamespace, methodName2, list, null);
        return s;
    }

    @GetMapping("delete")
    public String delete(String code) throws Exception {
//        K3CloudApiClient client = new K3CloudApiClient(K3CloudURL);
        K3CloudApiClient client = SpringBeanUtils.getBean(K3CloudApiClient.class);
        Boolean result = client.login(dbId, uid, pwd, lang);
        if(result){
            String sFormId = "SAL_SaleOrder";
            String sContent = "{\"Numbers\":[\""+code+"\"]}";
            String unAudit = client.unAudit(sFormId, sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);
            String delete = client.delete(sFormId, sContent);//("Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save", new Object[]{sFormId,sContent},String.class);
            ApiResult success = ApiResult.isSuccess(delete);
            if (success.getCode() == 1)
                System.out.println("delete success！");
                return "delete success！";
        }
        return "error！";
    }

    @GetMapping("test4")
    public String test4(String requestid) throws Exception {
        List<Map<String,String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("requestid", requestid);
        list.add(map);
        String s = CallWebServiceUtil.sendWsdl(url, targetNamespace, SaveInvoice, list, null);
        return s;
    }

    @GetMapping("testHuikuan")
    public String testHuikuan(String requestid) throws Exception {
        List<Map<String,String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("requestid", requestid);
        list.add(map);
        String s = CallWebServiceUtil.sendWsdl(url, targetNamespace, "SavePayment", list, null);
        return s;
    }

}

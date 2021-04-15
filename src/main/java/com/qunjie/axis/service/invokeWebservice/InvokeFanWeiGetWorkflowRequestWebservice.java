package com.qunjie.axis.service.invokeWebservice;

import com.qunjie.axis.config.FanWeiWorkflowConnectConfig;
import com.qunjie.axis.utils.CallWebServiceUtil;
import com.qunjie.common.util.SpringBeanUtils;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.impl.util.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by whs on 2020/12/2.
 */
public class InvokeFanWeiGetWorkflowRequestWebservice {

//    private static final String URL = "http://oa.qunje.com:88/services/WorkflowService?wsdl";
//
//    private static final String targetNamespace = "webservices.services.weaver.com.cn";

    private static final String GETWORKFLOWREQUEST = "getWorkflowRequest";

    private static Logger log = LoggerFactory.getLogger(InvokeFanWeiGetWorkflowRequestWebservice.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static final FanWeiWorkflowConnectConfig FAN_WEI_WORKFLOW_CONNECT_CONFIG = SpringBeanUtils.getBean(FanWeiWorkflowConnectConfig.class);


    /**
     * 取得流程详细信息
     * @param requestid
     * @return
     */
    public static JSONObject invokeGetWorkflowRequestWebservice(String requestid){
        String URL = FAN_WEI_WORKFLOW_CONNECT_CONFIG.getUrl();
        String targetNamespace = FAN_WEI_WORKFLOW_CONNECT_CONFIG.getTargetNamespace();

        log.debug("泛微调用webservice接口:requestid = "+requestid +"--------时间 = "+sdf.format(new Date()));
        List<Map<String,String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("requestid", requestid);//请求id
        map.put("userid", "1");//用户id
        map.put("fromrequestid", "0");//从相关id的工作流过来
        list.add(map);
        String response = CallWebServiceUtil.sendWsdl(URL, targetNamespace, GETWORKFLOWREQUEST, list, null);
//        log.debug("泛微调用webservice接口返回值 results = "+response);
        log.debug("泛微调用webservice接口结束--------时间 = "+sdf.format(new Date()));
        JSONObject jsonObject = XML.toJSONObject(response);
        return jsonObject;
    }
}

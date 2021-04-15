package com.qunjie.fanwei.webservice.dept;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.qunjie.axis.utils.CallWebServiceUtil;
import com.qunjie.common.util.CxfInvokeHelper;
import com.qunjie.fanwei.config.FanWeiConnectConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.oa.webservice.dept.DeptService
 *
 * @author whs
 * Date:   2020/12/18  10:15
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
@Slf4j
public class DeptService {

    @Autowired
    FanWeiConnectConfig fanWeiConnectConfig;

    private static final String GETHRMDEPARTMENTINFO = "getHrmDepartmentInfoXML";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public List<Deparment> getHrmDepartmentInfo2(String subcompanyId){
        String URL = fanWeiConnectConfig.getHrmUrl();
        String targetNamespace = fanWeiConnectConfig.getHrmTargetNamespace();
        log.debug("泛微调用webservice接口 获取所有部门信息--------时间 = "+sdf.format(new Date()));
        List<Map<String,String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if (null != address) {
            map.put("ipaddress", Constant.IP);//调用接口的IP地址
            map.put("subcompanyId", subcompanyId);//分部id，多个用逗号分隔（不为空时该条件有效
        }
        list.add(map);
        String response = CallWebServiceUtil.sendWsdl(URL, targetNamespace, GETHRMDEPARTMENTINFO, list, null);
        log.debug("泛微调用webservice接口返回值 response = "+response);
        log.debug("泛微调用webservice接口结束--------时间 = "+sdf.format(new Date()));
        return buildDepartmentFromXml(response);
    }

    public List<Deparment> getHrmDepartmentInfo(Integer subcompanyId){
        String URL = fanWeiConnectConfig.getHrmUrl();
        String targetNamespace = fanWeiConnectConfig.getHrmTargetNamespace();
        log.debug("泛微调用webservice接口 获取所有部门信息--------时间 = "+sdf.format(new Date()));
        String response = CxfInvokeHelper.invoke(URL,  GETHRMDEPARTMENTINFO,Constant.IP,String.valueOf(subcompanyId));
        log.debug("泛微调用webservice接口返回值 response = "+response);
        log.debug("泛微调用webservice接口结束--------时间 = "+sdf.format(new Date()));
        return buildDepartmentFromXml(response);
    }

    private List<Deparment> buildDepartmentFromXml(String source) {
        try {

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            JavaType type = xmlMapper.getTypeFactory().constructParametricType(List.class, Deparment.class);
            List<Deparment> deparments = xmlMapper.readValue(source, type);
            return deparments;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    /**
     * 部门映射Bean（从泛微获取）
     */
    @Data
    public static class Deparment {
        private String departmentid;
        private String fullname;
        private String subcompanyid;
        private String supdepartmentid;
        private String showorder;
        private String canceled;
    }

}

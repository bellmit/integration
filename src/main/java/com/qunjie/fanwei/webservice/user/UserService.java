package com.qunjie.fanwei.webservice.user;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.qunjie.axis.utils.CallWebServiceUtil;
import com.qunjie.common.util.CxfInvokeHelper;
import com.qunjie.fanwei.webservice.dept.Constant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qunjie.fanwei.config.FanWeiConnectConfig;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.oa.webservice.user.UserService
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
public class UserService {

    @Autowired
    FanWeiConnectConfig fanWeiConnectConfig;

    private static final String GETHRMUSERINFO = "getHrmUserInfoXML";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public List<User> getHrmUserInfo2(){
        String URL = fanWeiConnectConfig.getHrmUrl();
        String targetNamespace = fanWeiConnectConfig.getHrmTargetNamespace();
        log.debug("泛微调用webservice接口 获取所有人员信息--------时间 = "+sdf.format(new Date()));
        List<Map<String,String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        map.put("ipaddress", Constant.IP);
        map.put("workcode","");//人员编码（不为空时该条件有效）
        map.put("subcompanyId","");//分部id，多个用逗号分隔（不为空时该条件有效）
        map.put("departmentid","");//部门id，多个用逗号分隔（不为空时该条件有效）
        map.put("jobtitleid","");//岗位id，多个用逗号分隔（不为空时该条件有效）

        list.add(map);
        String response = CallWebServiceUtil.sendWsdl(URL, targetNamespace, GETHRMUSERINFO, list, null);
        log.debug("泛微调用webservice接口返回值 response = "+response);
        log.debug("泛微调用webservice接口结束--------时间 = "+sdf.format(new Date()));
        return buildUserFromXml(response);
    }

    /**
     *
     * @param workcode  人员编码（不为空时该条件有效）
     * @param subcompanyId  分部id，多个用逗号分隔（不为空时该条件有效）
     * @param departmentid  部门id，多个用逗号分隔（不为空时该条件有效）
     * @param jobtitleid    岗位id，多个用逗号分隔（不为空时该条件有效）
     * @return
     */
    public List<User> getHrmUserInfo(String workcode,String subcompanyId,String departmentid,String jobtitleid){
        String URL = fanWeiConnectConfig.getHrmUrl();
        String targetNamespace = fanWeiConnectConfig.getHrmTargetNamespace();
        log.debug("泛微调用webservice接口 获取所有人员信息--------时间 = "+sdf.format(new Date()));
        String response = CxfInvokeHelper.invoke(URL, GETHRMUSERINFO, Constant.IP,workcode,subcompanyId,departmentid,jobtitleid,"");
        log.debug("泛微调用webservice接口返回值 response = "+response);
        log.debug("泛微调用webservice接口结束--------时间 = "+sdf.format(new Date()));
        return buildUserFromXml(response);
    }


    private List<User> buildUserFromXml(String source) {
        try {

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            JavaType type = xmlMapper.getTypeFactory().constructParametricType(List.class, User.class);
            List<User> users = xmlMapper.readValue(source, type);
////            手机号为空不同步
//            return users.stream().filter(this::checkUserPhone).collect(Collectors.toList());
            return users;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private boolean checkUserPhone(User user) {
        if (!StringUtils.isEmpty(user.getMobile())) {
            return true;
        } else {
            log.error("用户电话号码格式不正确{}", user);
            return false;
        }
    }

    @Data
    public static class User {
        private String userid;
        private String departmentid;
        private String departmentname;
        private String mobile;
        private String loginid;
        private String lastname;
        private String email;
        private String sex;
        private String managerid;
        private String dsporder;
        //状态(1,正常，5，离职)
        private String status;
    }
}

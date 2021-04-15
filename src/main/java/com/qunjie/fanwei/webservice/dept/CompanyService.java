package com.qunjie.fanwei.webservice.dept;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.qunjie.common.util.CxfInvokeHelper;
import com.qunjie.fanwei.config.FanWeiConnectConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunje.oacrmbridge.oa.webservice.dept.CompanyService
 *
 * @author whs
 * Date:   2020/12/18  16:47
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Slf4j
@Service
public class CompanyService {

    @Autowired
    private FanWeiConnectConfig fanWeiConnectConfig;

    private static final String GETHRMSUBCOMPANYINFOXML = "getHrmSubcompanyInfoXML";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public List<Company> getHrmDepartmentInfo(){
        String URL = fanWeiConnectConfig.getHrmUrl();
        String targetNamespace = fanWeiConnectConfig.getHrmTargetNamespace();
        log.debug("泛微调用webservice接口 获取所有分部信息--------时间 = "+sdf.format(new Date()));
        String response = CxfInvokeHelper.invoke(URL,  GETHRMSUBCOMPANYINFOXML, Constant.IP);
        log.debug("泛微调用webservice接口返回值 response = "+response);
        log.debug("泛微调用webservice接口结束--------时间 = "+sdf.format(new Date()));
        return buildCompanyFromXml(response);
    }

    private List<Company> buildCompanyFromXml(String source) {
        try {

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            JavaType type = xmlMapper.getTypeFactory().constructParametricType(List.class, Company.class);
            List<Company> companys = xmlMapper.readValue(source, type);
            return companys;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Data
    public static class Company {
        //公司id
        private String subcompanyid;
        //公司名
        private String fullname;
        //父公司id
        private String supsubcompanyid;
        //停用
        private String canceled;
        //排序
        private String showorder;

    }

}

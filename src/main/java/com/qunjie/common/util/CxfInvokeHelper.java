package com.qunjie.common.util;/**
 * Created by whs on 2021/1/6.
 */

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.util.CxfInvokeHelper
 *
 * @author whs
 *         Date:   2021/1/6  17:33
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
public class CxfInvokeHelper {

    public static String invoke(String wsdlUrl,String methodName,Object... args){
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdlUrl);
        HTTPConduit conduit = (HTTPConduit)client.getConduit();
        HTTPClientPolicy policy = new HTTPClientPolicy();
        long timeout = 1* 60*1000;
        policy.setConnectionTimeout(timeout);
        policy.setReceiveTimeout(timeout);
        conduit.setClient(policy);
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(methodName, args);
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        return objects[0].toString();
    }
}

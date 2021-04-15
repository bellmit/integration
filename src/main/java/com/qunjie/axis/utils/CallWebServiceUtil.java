package com.qunjie.axis.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.NamedValue;
import org.apache.axis2.transport.http.HTTPConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class CallWebServiceUtil {

    /***
     * 调用Wsdl接口
     * @param url 接口地址，可不加?wsdl
     * @param targetNamespace 命名空间
     * @param methodName 方法名
     * @param params 参数
     * @param paramHeaders 请求头参数
     * @return {"head":"S=成功；F=失败","msg":"描述信息","data":"返回数据"}
     */
    public static String sendWsdl(String url, String targetNamespace, String methodName, List<Map<String, String>> params, List<NamedValue> paramHeaders) {
        String result = "";
        ServiceClient sender =null;
        try {
            String endpoint = url.replace("?wsdl", "");
            //直接引用远程的wsdl文件
            Options options = new Options();
            options.setTo(new EndpointReference(endpoint));
            options.setAction(targetNamespace + methodName);//解决可能出现的错误：服务器未能识别 HTTP 头 SOAPAction 的值: 。
            options.setTimeOutInMilliSeconds(20*1000);
            sender = new ServiceClient();
            sender.setOptions(options);

            OMFactory fac = OMAbstractFactory.getOMFactory();
            OMNamespace omNs = fac.createOMNamespace(targetNamespace, "");//参数1(uri)=即为wsdl文档的targetNamespace；参数2(prefix)=可不填
            OMElement method = fac.createOMElement(methodName, omNs);//方法名
            //设置参数
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    Map<String, String> map = params.get(i);
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String mapKey = entry.getKey();
                        String mapValue = entry.getValue();
                        OMElement in = fac.createOMElement(mapKey, omNs);//参数名
                        in.setText(mapValue);//参数值
                        method.addChild(in);
                    }
                }
            }

            //设置请求头参数
            List<NamedValue> headers = (List<NamedValue>) sender.getOptions().getProperty(HTTPConstants.HTTP_HEADERS);
            if (headers == null) {
                headers = new ArrayList<NamedValue>();
            }
            if (paramHeaders != null && paramHeaders.size() > 0) {
                headers.addAll(paramHeaders);
            }
            sender.getOptions().setProperty(HTTPConstants.HTTP_HEADERS, headers);

            OMElement resultEle = sender.sendReceive(method);
            System.out.println("调用wsdl接口结果：" + resultEle);
            result = resultEle.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != sender){
                try {
                    sender.cleanupTransport();
                } catch (AxisFault axisFault) {
                    log.error("wsdl接口调用关闭失败！");
                }
            }
        }
        return result;
    }
}
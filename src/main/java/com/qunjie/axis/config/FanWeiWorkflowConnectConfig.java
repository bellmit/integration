package com.qunjie.axis.config; /**
 * Created by whs on 2020/12/10.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.jindie.config.K3CloudCOnfig
 *
 * @author whs
 *         Date:   2020/12/10  18:28
 *         Description: 配置金蝶服务连接信息
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
@ConfigurationProperties(prefix = "fanwei.axis")
@Configuration
public class FanWeiWorkflowConnectConfig {

    private String url;

    private String targetNamespace;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTargetNamespace() {
        return targetNamespace;
    }

    public void setTargetNamespace(String targetNamespace) {
        this.targetNamespace = targetNamespace;
    }
}

package com.qunjie.jindie.config;/**
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
@ConfigurationProperties(prefix = "k3cloud")
@Configuration
public class K3CloudConfig {

    private String url;

    private String dbId;

    private String uid;

    private String pwd;

    private String lang;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "K3CloudConfig{" +
                "url='" + url + '\'' +
                ", dbId='" + dbId + '\'' +
                ", uid='" + uid + '\'' +
                ", pwd='" + pwd + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}

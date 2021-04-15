package com.qunjie.jindie.util;

import kingdee.bos.webapi.client.ApiClient;
import kingdee.bos.webapi.client.K3CloudApiClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.jindie.util.K3CloudSpringHelper
 *
 * @author whs
 * Date:   2021/1/22  13:57
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Component
@Data
public class K3CloudSpringHelper {
    @Value("${k3cloud.url}")
    private String K3CloudURL;

    @Bean
    public K3CloudApiClient k3CloudApiClient(){
        return new K3CloudApiClient(K3CloudURL);
    }
}

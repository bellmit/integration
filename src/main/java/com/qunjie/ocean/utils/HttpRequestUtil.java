package com.qunjie.ocean.utils;

import com.alibaba.fastjson.JSONObject;
import com.qunjie.ocean.servcie.TokenCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.ocean.utils.HttpRequestUtil
 *
 * @author whs
 * Date:   2021/3/4  17:03
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Slf4j
@Component
public class HttpRequestUtil {

    private static final String prefix = "https://ad.oceanengine.com/open_api/";

    @Autowired
    HttpHelper httpHelper;
    @Autowired
    TokenCache tokenCache;

    public JSONObject getAdvertiserInfo(Map data){
        String uri = "2/advertiser/info/";
        return httpHelper.doGet(prefix + uri,tokenCache.getToken().getAccess_token(), data);
    }

    public JSONObject getUserInfo(Map data){
        String uri = "2/user/info/";
        return httpHelper.doGet(prefix+uri,tokenCache.getToken().getAccess_token(),data);
    }

    public JSONObject getAccessToken(Map data){
        String uri = "oauth2/advertiser/get/";
        data.put("access_token",tokenCache.getToken().getAccess_token());
        data.put("secret",TokenCache.SECRET);
        data.put("app_id",TokenCache.APP_ID);
        return httpHelper.doGet(prefix+uri,null,data);
    }

    public JSONObject getClueList(Map data){
        String uri = "2/tools/clue/get/";
        return httpHelper.doGet(prefix+uri,tokenCache.getToken().getAccess_token(),data);
    }

}

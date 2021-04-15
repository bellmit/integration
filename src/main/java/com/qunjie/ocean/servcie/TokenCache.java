package com.qunjie.ocean.servcie;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.qunjie.common.email.DefaultEmailAddress;
import com.qunjie.common.email.event.SendEmailEvent;
import com.qunjie.ocean.model.TokenModel;
import com.qunjie.ocean.utils.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.ocean.servcie.TokenCache
 *
 * @author whs
 * Date:   2021/3/1  10:51
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Slf4j
@Component
public class TokenCache {

    @Autowired
    HttpHelper httpHelper;
    @Autowired
    ApplicationContext applicationContext;

    private static Map<String, TokenModel> accessTokenMap = Maps.newConcurrentMap();

    public static final String APP_ID = "1692668295491611";

    public static final String SECRET = "915a689fe0b4c496f061ff1c83264ac1ec80b1f6";

    public static final String ADVERTISER_ID = "1690201024478285";

    public static final String key = APP_ID + SECRET;

    public synchronized void setToken(TokenModel token){
        accessTokenMap.put(key,token);
    }

    public synchronized TokenModel getToken(){
        TokenModel tokenModel = accessTokenMap.computeIfAbsent(key,k->new TokenModel());
        return tokenModel;
    }

    public void refreshToken(){
        TokenModel tokenModel = accessTokenMap.computeIfAbsent(key,k->new TokenModel());
        if (tokenModel != null){
            String refresh_token = tokenModel.getRefresh_token();
            TokenModel newTokenModel = refreshAccessToken(refresh_token);
            setToken(newTokenModel);
        }else {

        }

    }


    public TokenModel getAccessToken(String code) {

        // 请求地址
        String open_api_url_prefix = "https://ad.oceanengine.com/open_api/";
        String uri = "oauth2/access_token/";

        // 请求参数
        Map<String, String> data = new HashMap() {
            {
                put("app_id", TokenCache.APP_ID);
                put("secret", TokenCache.SECRET);
                put("grant_type", "auth_code");
                put("auth_code", code);
            }
        };
        JSONObject jsonObject = httpHelper.doPost(open_api_url_prefix + uri, data);
        if (jsonObject != null){
            JSONObject data1 = jsonObject.getJSONObject("data");
            TokenModel tokenModel = new TokenModel(data1.getString("access_token"),data1.getLong("expires_in"),
                    data1.getString("refresh_token"),data1.getLong("refresh_token_expires_in"));
            setToken(tokenModel);
            return tokenModel;
        }
        return null;
    }

    public TokenModel refreshAccessToken(String refresh_token) {
        // 请求地址
        String open_api_url_prefix = "https://ad.oceanengine.com/open_api/";
        String uri = "oauth2/refresh_token/";

        // 请求参数1
        Map < String,String> data = new HashMap() {
            {
                put("appid", TokenCache.APP_ID);
                put("secret", TokenCache.SECRET);
                put("grant_type", "refresh_token");
                put("refresh_token", refresh_token);
            }
        };
        // 构造请求
        JSONObject jsonObject = httpHelper.doPost(open_api_url_prefix + uri, data);

        if (jsonObject != null) {
            JSONObject data1 = jsonObject.getJSONObject("data");
            TokenModel tokenModel = new TokenModel(data1.getString("access_token"), data1.getLong("expires_in"),
                    data1.getString("refresh_token"), data1.getLong("refresh_token_expires_in"));
            return tokenModel;
        }
        return null;
    }



    @Scheduled(cron = "0 0 0/12 * * ?")
    public void refreshToke(){
        log.info("==============refresh缓存的token！================================");
        TokenModel tokenModel = accessTokenMap.get(key);
        if (tokenModel != null){
            TokenModel tokenModel1 = refreshAccessToken(tokenModel.getRefresh_token());
            setToken(tokenModel1);
        }else {
            applicationContext.publishEvent(new SendEmailEvent(this,"飞鱼销售线索未授权，到如下地址:\nhttps://ad.oceanengine.com/openapi/appid/list.html?rid=7ag3hu8nekn#/external/\n" +
                    "登录后点APPID管理--->点击test那个猫--->点击下方的'点击跳转'-->完成授权\n账户：qjwl1@gooddian.cn\n" +
                    "密码：Gd123456!", DefaultEmailAddress.SENDTODev,"飞鱼销售线索未授权！"));
            log.info("==================缓存的token失效=====需重新授权！================");
        }
    }

}

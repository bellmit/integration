package com.qunjie.ocean.servcie;

import com.alibaba.fastjson.JSONObject;
import com.qunjie.ocean.utils.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.ocean.servcie.TService
 *
 * @author whs
 * Date:   2021/3/4  16:18
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Service
public class OCeanService {

    @Autowired
    HttpRequestUtil httpRequestUtil;

    public JSONObject getAdvertiserInfo(Long[] ids) {

        // 请求参数
        Map data = new HashMap<>();
        data.put("advertiser_ids", ids);
        data.put("fields", new String[] {"id", "name", "email", "status"});
        JSONObject jsonObject = httpRequestUtil.getAdvertiserInfo( data);
        return jsonObject;
    }

    public JSONObject getUserInfo(){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonObject = httpRequestUtil.getUserInfo(map);
        return jsonObject;
    }

    public JSONObject getAccessToken() {
        // 请求参数
        Map<String,Object> data = new HashMap<>();
        JSONObject accessToken = httpRequestUtil.getAccessToken(data);
        return accessToken;
    }

    public JSONObject getClueList(String start_time,String end_time,Integer page){
        final String[] advertiser_ids = new String[]{TokenCache.ADVERTISER_ID};
        // 请求参数
        Map<String,Object> data = new HashMap<>();
        data.put("advertiser_ids", advertiser_ids);
        data.put("start_time", start_time);
        data.put("end_time", end_time);
        data.put("page_size",100);
        if (page != null) {
            data.put("page", page);
        }
        return httpRequestUtil.getClueList(data);
    }
}

package com.qunjie.ocean.controller;

import com.alibaba.fastjson.JSONObject;
import com.qunjie.ocean.clue.service.OceanClueService;
import com.qunjie.ocean.model.TokenModel;
import com.qunjie.ocean.servcie.OCeanService;
import com.qunjie.ocean.servcie.TokenCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.ocean.controller.TestController
 *
 * @author whs
 * Date:   2021/3/1  10:02
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("/ocean")
public class TestController {

    @Autowired
    TokenCache tokenCache;
    @Autowired
    OCeanService tService;
    @Autowired
    OceanClueService oceanClueService;

    /**
     * 授权
     * @param state
     * @param auth_code
     * @return
     */
    @GetMapping("/openapi")
    public String openapi(@RequestParam("state") String state,@RequestParam("auth_code")String auth_code){
        System.out.println(auth_code);
        tokenCache.getAccessToken(auth_code);
        System.out.println("授权成功！");
        return "授权成功！";
    }

    @GetMapping("test")
    public String test(){
        return "12324";
    }

    /**
     * 广告主信息
     * @param ids
     * @return
     */
    @GetMapping("getAd")
    public JSONObject getAd(String ids){
        String[] split = ids.split("[,，、]]");
        Long[] longs = Arrays.stream(split).map(Long::parseLong).toArray(Long[]::new);
        JSONObject advertiserInfo = tService.getAdvertiserInfo(longs);
        return advertiserInfo;
    }

    /**
     * 获取已授权账号
     * @return
     */
    @GetMapping("getAccessToken")
    public JSONObject getAccessToken(){
        return tService.getAccessToken();
    }

    /**
     * 获取token中用户信息
     * @return
     */
    @GetMapping("getUserInfo")
    public JSONObject getUserInfo(){
        return tService.getUserInfo();
    }

    /**
     * 获取token值
     * @return
     */
    @GetMapping("/getToken")
    public TokenModel getToken(){
        return tokenCache.getToken();
    }

    /**
     * 刷新token
     */
    @GetMapping("refreshToken")
    public void refreshToken(){
        tokenCache.refreshToken();
    }

    @GetMapping("getClueList")
    public JSONObject getClueList(@RequestParam("start") String start,@RequestParam("end") String end){
        return tService.getClueList(start,end,null);
    }

    @GetMapping("syncClueList")
    public int getClueList2(@RequestParam("start") String start,@RequestParam("end") String end){
        oceanClueService.syncClue(start,end);
        return 1;
    }

    @GetMapping("syncClueListById")
    public int getClueList2(@RequestParam("start") String start,@RequestParam("end") String end,@RequestParam("ids")String ids){
        if (StringUtils.isBlank(ids)) {
            oceanClueService.syncClue(start, end);
        }else {
            List<String> strings = Arrays.asList(ids.split("[,，、]"));
            oceanClueService.syncClue(start,end,strings);
        }
        return 1;
    }
}

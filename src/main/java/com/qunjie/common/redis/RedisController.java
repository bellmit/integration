package com.qunjie.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.redis.RedisController
 *
 * @author whs
 * Date:   2021/4/7  14:32
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @GetMapping("setHash")
    public int setHash(){
        redisTemplate.opsForHash().put("com:qunje:redis","司海旭","小呆");
        return 1;
    }

    @GetMapping("getHash")
    public String getHash(){
        String value = (String)redisTemplate.opsForHash().get("com:qunje:redis", "司海旭");
        return value;
    }

    @GetMapping("setString")
    public int setString(){
        redisTemplate.opsForValue().set("司海旭","小呆",50, TimeUnit.SECONDS);
        return 1;
    }

    @GetMapping("setList")
    public int setList(){
        redisTemplate.opsForList().rightPush("list","rightPush");
        redisTemplate.opsForList().leftPush("list","leftPush");
        return 1;
    }

    @GetMapping("setSet")
    public int setSet(){
        redisTemplate.opsForSet().add("set","set1");
        redisTemplate.opsForSet().add("set","set2");
        return 1;
    }

    @GetMapping("setZset")
    public int setZset(){
        redisTemplate.opsForZSet().add("zset","zset2",2);
        redisTemplate.opsForZSet().add("zset","zset1",1);
        return 1;
    }
}

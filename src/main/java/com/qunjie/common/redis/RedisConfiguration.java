package com.qunjie.common.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Set;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.redis.RedisConfiguration
 *
 * @author whs
 * Date:   2021/4/7  9:04
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
@Configuration
@Slf4j
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfiguration {

    private String host;

    private Integer port;

    private String password;

    private Integer database;

    @Value("${spring.redis.sentinel.master:}")
    private String master;

    @Value("${spring.redis.sentinel.nodes:}")
    private Set<String> nodes;
    //常规类型(独立)
    private final static String REDIS_STANDALONE = "standalone";
    //哨兵模式
    private final static String REDIS_SENTINEL = "sentinel";
    private final static String REDISSION_PREFIX_URL = "redis://";

    /**
     * Redis工厂类注入
     * @return
     */
    @Bean
    public RedisConnectionFactory RedisConnectionFactory(){
        log.info("Redis连接工厂初始化：RedisConnectionFactory is initializing...");
        LettuceConnectionFactory lettuceConnectionFactory = this.dynamicLettuceFactory();
        if (ObjectUtils.isEmpty(lettuceConnectionFactory)){
            log.info("Redis连接工厂初始化失败");
            throw  new RuntimeException("core.redis.factory.init.error");
        }
        log.info("redis连接工厂初始化 - 完成");
        return lettuceConnectionFactory;
    }

    /**
     * RedisConfiguration 动态获取
     * @return
     */
    private LettuceConnectionFactory dynamicLettuceFactory(){
        boolean isSuccess = false;
        LettuceConnectionFactory lettuceConnectionFactory = null;
        LettuceClientConfiguration.LettuceClientConfigurationBuilder lettuceClientConfigurationBuilder = LettucePoolingClientConfiguration
                .builder().clientOptions(this.clientOptions()).clientResources(this.clientResources());
        if (this.checkRedisConfig(REDIS_SENTINEL)) {
            try {
                log.info("初始化redis工厂 ： 哨兵模式");
                RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration(master, nodes);
                redisSentinelConfiguration.setDatabase(database);
                redisSentinelConfiguration.setPassword(RedisPassword.of(password));
                redisSentinelConfiguration.setSentinelPassword(RedisPassword.of(password));
                lettuceConnectionFactory = new LettuceConnectionFactory(redisSentinelConfiguration,
                        lettuceClientConfigurationBuilder.build());
                log.info("初始化Redis工厂：哨兵模式 - 完成");
                isSuccess = true;
            } catch (Exception e) {
                log.error("初始化哨兵模式失败：原因"+ e.getMessage());
            }
        }
        if (this.checkRedisConfig(REDIS_STANDALONE) && !isSuccess){
            try {
                log.info("初始化redis工厂：单实例模式");
                RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
                redisStandaloneConfiguration.setHostName(host);
                redisStandaloneConfiguration.setPort(port);
                redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
                redisStandaloneConfiguration.setDatabase(database);
                lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration,
                        lettuceClientConfigurationBuilder.build());
                log.info("初始化Redis工厂：单实例模式 - 完成");
                isSuccess = true;
            } catch (Exception e) {
                log.error("初始化Redis单实例模式失败，原因："+ e.getMessage());
            }
        }
        if (!isSuccess){
            return null;
        }
        lettuceConnectionFactory.setValidateConnection(true);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }

    private ClientOptions clientOptions(){
        return ClientOptions.builder().disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS).autoReconnect(true).build();
    }

    private ClientResources clientResources(){
        return DefaultClientResources.create();
    }

    /**
     * redis模式参数验证
     * @param mode
     * @return
     */
    private boolean checkRedisConfig(String mode){
        switch (mode){
            case REDIS_STANDALONE:
                return this.standloneCheck();
            case REDIS_SENTINEL:
                return this.sentinelCheck();
        }
        return false;
    }

    /**
     * 哨兵参数验证
     * @return
     */
    private boolean sentinelCheck() {
        return !StringUtils.isEmpty(master) && !CollectionUtils.isEmpty(nodes)
                && !StringUtils.isEmpty(password) && ObjectUtils.isEmpty(database);
    }

    /**
     * 常规模式参数验证
     * @return
     */
    private boolean standloneCheck() {
        return !StringUtils.isEmpty(host) && !ObjectUtils.isEmpty(port)
                && !StringUtils.isEmpty(password) && !ObjectUtils.isEmpty(database);
    }

    @Bean
    public RedisTemplate<String,?> redisTemplate(RedisConnectionFactory factory){
        log.info("redisTemplate初始化：redisTemplate is initializing...");
        Jackson2JsonRedisSerializer<Object> jackson = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson.setObjectMapper(objectMapper);
        RedisTemplate<String,?> template = new RedisTemplate<>();
        //使用手动注入的工厂类
        template.setConnectionFactory(factory);
        //采用json序列化
        template.setValueSerializer(jackson);
        //使用StringRedisSerializer来序列化和反序列化redis的key
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson);
        template.afterPropertiesSet();
        log.info("redisTemplate初始化 - 完成");
        return template;
    }

    @Bean
    public RedissonClient redissonClient(){
        log.info("redissonClient初始化：redissonClient is initializing...");
        RedissonClient redissonClient = this.dynamicRedission(new Config());
        if (ObjectUtils.isEmpty(redissonClient)){
            log.info("redissonClient初始化 - 失败");
            throw new RuntimeException("core.redission.init.error");
        }
        log.info("redissionClient初始化 - 完成");
        return redissonClient;
    }

    private RedissonClient dynamicRedission(Config config) {
        boolean isSuccess = false;
        RedissonClient redissonClient = null;
        if (this.checkRedisConfig(REDIS_SENTINEL)){
            try {
                log.info("初始化redisson：哨兵模式");
                String[] sentinelUrls = new String[nodes.size()];
                ArrayList<String> cNodes = new ArrayList<>(nodes);
                cNodes.forEach(node->{
                    sentinelUrls[cNodes.indexOf(node)] = REDISSION_PREFIX_URL + node;
                });
                SentinelServersConfig sentinelServers = config.useSentinelServers();
                sentinelServers.setMasterName(master);
                sentinelServers.addSentinelAddress(sentinelUrls);
                sentinelServers.setDatabase(database);
                sentinelServers.setPassword(password);
                log.info("初始化Redisson：哨兵模式 - 完成");
                isSuccess = true;
                redissonClient = Redisson.create(config);
            } catch (Exception e) {
                log.info("初始化Redisson哨兵模式失败：原因："+ e.getMessage());
            }
        }
        if (this.checkRedisConfig(REDIS_STANDALONE) && !isSuccess){
            try {
                log.info("初始化Redisson: 单实例模式");
                SingleServerConfig singleServer = config.useSingleServer();
                singleServer.setAddress(String.format("redis://%s:%s",host,port));
                singleServer.setPassword(password);
                singleServer.setDatabase(database);
                log.info("初始化Redisson：单实例模式 - 完成");
                isSuccess = true;
                redissonClient = Redisson.create(config);
            } catch (Exception e) {
                log.error("初始化Redisson单实例模式失败：原因：" + e.getMessage());
            }
        }
        if (!isSuccess){
            return null;
        }
        return redissonClient;
    }

}

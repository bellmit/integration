package com.qunjie.common.mq;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.common.mq.MQBaseQueue
 *
 * @author whs
 * Date:   2021/4/6  10:54
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public abstract class MQBaseQueue {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ConnectionFactory connectionFactory;
    @Autowired
    RabbitAdmin rabbitAdmin;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedissonClient redissonClient;

    private Queue queue;
    private Queue queueDead;

    private String queueName;
    private String queueDeadName;

    protected DirectExchange exchange;
    protected DirectExchange exchangeDead;

    private String exchangeDeadName;

    protected String routeKey;
    private String routeDeadKey;
    private final String RETRY_REDIS_KEY = "MQ_RETRY_";

    protected abstract void onMessage(String message) throws Exception;

    protected String getQueueName(){
        return this.getClass().getName();
    }
    private int getConsumeCount(String qname){
        return (int)rabbitAdmin.getQueueProperties(qname).get("QUEUE_CONSUMER_COUNT");
    }

    protected  int getMessageCount(String qname){
        return (int)rabbitAdmin.getQueueProperties(qname).get("QUEUE_MESSAGE_COUNT");
    }

    protected DirectExchange getExchange(){
        DirectExchange ex = new DirectExchange(queueName + ".exchange");
        rabbitAdmin.declareExchange(ex);
        return ex;
    }

    protected DirectExchange getDeadExchange(){
        DirectExchange ex = new DirectExchange(exchangeDeadName);
        rabbitAdmin.declareExchange(ex);
        return ex;
    }

    protected String getRouteKey(){
        return queueName + ".route";
    }

    protected int getMessageTTL(){
        return 0;
    }

    protected int getDeadMessageTTL(){
        return 0;
    }

    protected int getRetryTime(){
        return 1000*60*10;
    }

    protected boolean isStartListening(){
        return true;
    }

    protected boolean isSingleConsumer(){
        return false;
    }

    protected boolean isAutoDelete(){
        return false;
    }

    protected Map<String,Object> getArgs(){
        HashMap<String,Object> queueArgs = new HashMap<>();
        queueArgs.put("x-dead-letter-exchange",exchangeDeadName);
        queueArgs.put("x-dead-letter-routing-key",routeDeadKey);
        if (getMessageTTL() > 0){
            queueArgs.put("x-message-ttl", getMessageTTL());
        }
        return queueArgs;
    }

    protected Map<String,Object> getDeadArgs(){
        HashMap<String, Object> queueArgs = new HashMap<>();
        int deadMessageTTL = getDeadMessageTTL();
        if (deadMessageTTL > 0){
            queueArgs.put("x-message-ttl",deadMessageTTL);
        }
        return queueArgs;
    }

    protected boolean isRetry(){
        return true;
    }

    /**
     * 消费者每次从队列中获取信息的数量，值越高吞吐量越大，默认为1可以保证并发性
     * @return
     */
    protected int getPrefetchCount(){
        return 1;
    }

    private String getRetryKey(String queueName){
        return RETRY_REDIS_KEY + queueName;
    }


    public void initQueue(){
        queueName = getQueueName();
        queueDeadName = queueName + ".dead";
        exchangeDeadName = queueName + ".exchange.dead";
        routeKey = getRouteKey();
        routeDeadKey = queueName + ".route.dead";

        queue = new Queue(queueName, true, false, isAutoDelete(), getArgs());
        rabbitAdmin.declareQueue(queue);
        queueDead = new Queue(queueDeadName, true, false, isAutoDelete(), getDeadArgs());
        rabbitAdmin.declareQueue(queueDead);

        exchange = getExchange();
        exchangeDead = getDeadExchange();
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(routeKey));
        rabbitAdmin.declareBinding(BindingBuilder.bind(queueDead).to(exchangeDead).with(routeDeadKey));

        if (isStartListening()){
            RLock lock = null;
            if (isSingleConsumer()){
                lock = redissonClient.getLock(queueName);
                lock.lock();
                int consumeCount = getConsumeCount(queueName);

                if (consumeCount > 0){
                    logger.info("队列["+queueName+"]为单消费模式，已在其他服务监听，本服务不启动");
                    lock.unlock();
                    return;
                }
            }
            startListener();
            startDeadListener();
            logger.info("队列["+queueName+"]消费者监听成功");
            if (lock != null){
                lock.unlock();
            }
        }
    }


    private void startDeadListener(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queueDead);
        container.setExposeListenerChannel(true);
        container.setPrefetchCount(getPrefetchCount());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener((ChannelAwareMessageListener)(message,channel)->{
            String messageBody = null;
            try {
                messageBody = new String(message.getBody(),"UTF-8");
                logger.info("死信消息处理："+message.getMessageProperties().getReceivedRoutingKey() + ":"
                        + message.getMessageProperties() + ";"+ messageBody);
                onMessage(messageBody);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                stringRedisTemplate.opsForValue().set(getRetryKey(queueDeadName),"false");
            } catch (Exception e) {
                String isRetry = stringRedisTemplate.opsForValue().get(getRetryKey(queueDeadName));
                if (isRetry() && isRetry != null && isRetry.equals("true")){
                    Thread.sleep(getRetryTime());
                }
                if (isRetry()){
                    logger.error("死信消息已处理失败，延时"+ getRetryTime()/1000/60 + "分钟执行",e);
                    stringRedisTemplate.opsForValue().set(getRetryKey(queueDeadName),"true");
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                }else {
                    logger.info("死信消息已处理失败，消息丢弃",e);
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
                }
            }
        });
    }

    /**
     * 启动监听
     */
    private void startListener(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue);
        container.setExposeListenerChannel(true);
        container.setPrefetchCount(getPrefetchCount());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener((ChannelAwareMessageListener)(message,channel)->{
            String messageBody = new String(message.getBody(),"UTF-8");
            if (isSingleConsumer() && getConsumeCount(queueName) > 1){
                String errorInfo = "单消费者队列["+queueName+"]出现多消费者";
                logger.error(errorInfo);
            }
            new Thread(() -> {
                try {
                    onMessage(messageBody);
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                } catch (Exception e) {
                    logger.error("消息已处理失败，进入死信队列", e.getMessage());
                    try {
                        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        });
        container.start();
    }

}

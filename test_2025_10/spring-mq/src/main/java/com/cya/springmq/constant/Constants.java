package com.cya.springmq.constant;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/19 20:26
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class Constants {
public static final String WORK_QUEUE="work_queue";
//发布订阅
public static final String FANOUT_QUEUE1="fanout_queue1";
    public static final String FANOUT_QUEUE2="fanout_queue2";
    public static final String FANOUT_EXCHANGE="fanout_exchange";
//路由模式

    public static final String DIRECT_QUEUE1="direct_queue1";
    public static final String DIRECT_QUEUE2="direct_queue2";
    public static final String DIRECT_EXCHANGE="dierct_exchange";
//    通配符模式
public static final String TOPIC_QUEUE1="topic_queue1";
    public static final String TOPIC_QUEUE2="topic_queue2";
    public static final String TOPIC_EXCHANGE="topic_exchange";




}
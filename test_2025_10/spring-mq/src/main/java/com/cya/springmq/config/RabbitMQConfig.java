package com.cya.springmq.config;

import com.cya.springmq.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class RabbitMQConfig {

    @Bean("workQueue")
    public Queue workQueue(){
//        声明队列
return QueueBuilder.durable(Constants.WORK_QUEUE).build();
    }

//    发布订阅
    @Bean("fanout_queue1")
    public Queue fanoutQueue1(){
        return QueueBuilder.durable(Constants.FANOUT_QUEUE1).build();
    }
    @Bean("fanout_queue2")
    public Queue fanoutQueue2(){
        return QueueBuilder.durable(Constants.FANOUT_QUEUE2).build();
    }
    @Bean("fanout_exchange")
    public FanoutExchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange(Constants.FANOUT_EXCHANGE).durable(true).build();
    }

    @Bean("fanoutQueueBingding1")
    public Binding fanoutQueueBingding(FanoutExchange fanoutExchange,@Qualifier("fanout_queue1") Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    @Bean("fanoutQueueBingding2")
    public Binding fanoutQueueBingding2(FanoutExchange fanoutExchange,@Qualifier("fanout_queue2") Queue queue){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

}
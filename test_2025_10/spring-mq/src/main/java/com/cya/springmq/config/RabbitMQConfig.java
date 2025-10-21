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
//    路由模式
@Bean("directQueue1")
public Queue directQueue1(){
    return QueueBuilder.durable(Constants.DIRECT_QUEUE1).build();
}
    @Bean("directQueue2")
    public Queue directQueue2(){
        return QueueBuilder.durable(Constants.DIRECT_QUEUE2).build();
    }
    @Bean("directExchange")
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange(Constants.DIRECT_EXCHANGE).durable(true).build();
    }

    @Bean("directQueueBingding1")
    public Binding directQueueBingding1(@Qualifier("directExchange") DirectExchange directExchange,@Qualifier("directQueue1") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("orange");
    }

    @Bean("directQueueBingding2")
    public Binding directQueueBingding2(@Qualifier("directExchange") DirectExchange directExchange,@Qualifier("directQueue2") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("black");
    }
    @Bean("directQueueBingding3")
    public Binding directQueueBingding3(@Qualifier("directExchange") DirectExchange directExchange,@Qualifier("directQueue2") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("green");
    }
//    通配符模式

    @Bean("topicQueue1")
public Queue topicQueue1(){
        return QueueBuilder.durable(Constants.TOPIC_QUEUE1).build();
}
    @Bean("topicQueue2")
    public Queue topicQueue2(){
        return QueueBuilder.durable(Constants.TOPIC_QUEUE2).build();
    }

    @Bean("topicExchange")
    public TopicExchange topicExchange(){
        return ExchangeBuilder.topicExchange(Constants.TOPIC_EXCHANGE).durable(true).build();
    }
    @Bean("topicQueueBinging1")
    public Binding topicQueueBinging1(@Qualifier("topicExchange") TopicExchange topicExchange,@Qualifier("topicQueue1") Queue queue){
        return BindingBuilder.bind(queue).to(topicExchange).with("*.orange.*");
    }
    @Bean("topicQueueBinging2")
    public Binding topicQueueBinging2(@Qualifier("topicExchange") TopicExchange topicExchange,@Qualifier("topicQueue2") Queue queue){
        return BindingBuilder.bind(queue).to(topicExchange).with("*.*.rabbit");
    }
    @Bean("topicQueueBinging3")
    public Binding topicQueueBinging3(@Qualifier("topicExchange") TopicExchange topicExchange,@Qualifier("topicQueue2") Queue queue){
        return BindingBuilder.bind(queue).to(topicExchange).with("lazy.#");
    }

}
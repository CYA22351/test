package com.cya.orderservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/21 17:01
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class RabbitConfig {

    @Bean("orderQueue")
    public Queue orderQueue(){
        return QueueBuilder.durable("order.  ").build();
    }
@Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,Jackson2JsonMessageConverter jackson2JsonMessageConverter){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

}
package com.cya.logisticsservice.config;

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
// */
@Configuration
public class RabbitConfig {

//    @Bean("orderQueue")
//    public Queue orderQueue(){
//        return QueueBuilder.durable("order.create").build();
//    }
@Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverterJ(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,Jackson2JsonMessageConverter jsonMessageConverterJ){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverterJ);
        return rabbitTemplate;
    }

}
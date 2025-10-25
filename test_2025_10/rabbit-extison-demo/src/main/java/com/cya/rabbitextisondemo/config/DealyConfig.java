package com.cya.rabbitextisondemo.config;

import com.cya.rabbitextisondemo.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/25 16:51
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class DealyConfig {

    @Bean("delayqueue")
    public Queue delayqueue(){
        return QueueBuilder.durable(Constants.DELAY_QUEUE).build();
    }

    @Bean("delayExchange")
    public Exchange delayExchange(){
        return ExchangeBuilder.directExchange(Constants.DELAY_EXCHANGE).delayed().build();
    }
    @Bean("deleyBing")
    public Binding deleyBing(@Qualifier("delayqueue") Queue queue, @Qualifier("delayExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("delay").noargs();
    }

    @Bean("transqueue")
    public Queue transqueue(){
        return QueueBuilder.durable(Constants.TRANS_QUEUE).build();
    }
}
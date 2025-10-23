package com.cya.rabbitextisondemo.config;

import com.cya.rabbitextisondemo.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.DocFlavor;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/23 15:43
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class RabbitConfig {



    @Bean("ackQueue")
    public Queue ackQueue(){
        return QueueBuilder.durable(Constants.ACK_QUEUE).build();
    }
    @Bean("ackExchange")
    public DirectExchange ackExchange(){
        return ExchangeBuilder.directExchange(Constants.ACK_EXCHANGE).durable(true).build();
    }
    @Bean("ackBing")
    public Binding ackBing(@Qualifier("ackQueue") Queue queue,@Qualifier("ackExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("ack").noargs();
    }


    @Bean("presQueue")
    public Queue presQueue(){
        return QueueBuilder.nonDurable(Constants.PRES_QUEUE).build();
    }

    @Bean("preExchange")
    public DirectExchange preExchange(){
        return ExchangeBuilder.directExchange(Constants.PRES_EXCHANGE).durable(false).build();
    }
@Bean("perBingding")
    public Binding perBingding(@Qualifier("preExchange") Exchange directExchange,@Qualifier("presQueue") Queue queue){
        return  BindingBuilder.bind(queue).to(directExchange).with("pres").noargs();
    }

//发送确认
    @Bean("confirmqueue")
    public Queue confirmqueue(){
        return QueueBuilder.durable(Constants.CONFIRM_QUEUE).build();
    }
    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
        return ExchangeBuilder.directExchange(Constants.CONFIRM_EXCHANGE).build();
    }
    @Bean("confirmBing")
    public Binding confirmBing(@Qualifier("confirmqueue") Queue queue,@Qualifier("confirmExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("confirm").noargs();
    }
    @Bean("retryqueue")
    public Queue retryqueue(){
        return QueueBuilder.durable(Constants.RETRY_QUEUE).build();
    }
    @Bean("retryExchange")
    public DirectExchange retryExchange(){
        return ExchangeBuilder.directExchange(Constants.RETRY_EXCHANGE).build();
    }
    @Bean("retryBind")
    public Binding retyrBing(@Qualifier("retryqueue") Queue queue,@Qualifier("retryExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("retry").noargs();
    }



}
package com.cya.rabbitextisondemo.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/23 17:17
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class RabbitTempConfig {
    @Bean("cconfirmrabbitTemplate")
public RabbitTemplate cconfirmrabbitTemplate(ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
    return rabbitTemplate;
}

    @Bean("confirmrabbitTemplate")
    public RabbitTemplate confirmrabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("执行confirm方法");
                if (b){
                    System.out.printf("接收到消息，消息ID：%s",correlationData==null? null:correlationData.getId());
                }
                else {
                    System.out.printf("未接收到消息，消息ID：%s,case: %s",correlationData==null? null:correlationData.getId(),s);

                }
            }
        });
//        消息被撤回，回调方法
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("消息被撤回："+returnedMessage );
            }
        });
        return rabbitTemplate;
    }}
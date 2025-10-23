package com.cya.rabbitextisondemo.controller;

import com.cya.rabbitextisondemo.constant.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/23 15:49
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Resource(name = "cconfirmrabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Resource(name = "confirmrabbitTemplate")
    private RabbitTemplate confirmrabbitTemplate;

    @RequestMapping("/pres")
    public String pres(){
        Message message=new Message("Presistent test ...".getBytes(),new MessageProperties());
//   消息持久化
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        System.out.println(message);
        rabbitTemplate.convertAndSend(Constants.PRES_EXCHANGE,"pres",message);
        return "发送成功";
    }

    @RequestMapping("/confirm")
    public String confirm(){
//        设置回调方法

        CorrelationData correlationData=new CorrelationData("1");
        confirmrabbitTemplate.convertAndSend(Constants.CONFIRM_EXCHANGE+1,"confirm","confirm test...",correlationData);
        return "消息发送成功";
    }
    @RequestMapping("/returns")
    public String returns(){
//        设置回调方法

        CorrelationData correlationData=new CorrelationData("15");
        confirmrabbitTemplate.convertAndSend(Constants.CONFIRM_EXCHANGE,"confirm111","confirm test...",correlationData);
        return "消息发送成功";
    }

    @RequestMapping("/retry")
    public String retry(){
        rabbitTemplate.convertAndSend(Constants.RETRY_EXCHANGE,"retry","tetyr test...");
        return "消息发送成功";
     }
}
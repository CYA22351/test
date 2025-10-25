package com.cya.rabbitextisondemo.controller;

import com.cya.rabbitextisondemo.constant.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @Resource(name = "transRabbitTemplate")
    private RabbitTemplate transRabbitTemplate;

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
    @RequestMapping("/delay2")
    public String delay2(){
        System.out.println("delay2...");
        rabbitTemplate.convertAndSend(Constants.DELAY_EXCHANGE,"delay","delay test 10 ...",message ->{
            message.getMessageProperties().setDelayLong(10000l);
            return message;
        } );
        rabbitTemplate.convertAndSend(Constants.DELAY_EXCHANGE,"delay","delay test 30s ...",message ->{
            message.getMessageProperties().setDelayLong(30000l);
            return message;
        } );
        System.out.printf("%tc 消息发送成功\n",new Date());
        return "消息发送成功";
    }

    @Transactional
    @RequestMapping("/trans")
    public String trans(){
        System.out.println("事务测试");
        transRabbitTemplate.convertAndSend("",Constants.TRANS_QUEUE,"trans test 1 ...");
        int n=5/0;
        transRabbitTemplate.convertAndSend("",Constants.TRANS_QUEUE,"trans test 2 ...");
        return "消息发送成功";

    }
}
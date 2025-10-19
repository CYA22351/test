package com.cya.springmq.controller;

import com.cya.springmq.constant.Constants;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/19 20:31
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Constants constants;

    @RequestMapping("/work")
    public String work(){
        for (int i=0;i<10;i++){
            rabbitTemplate.convertAndSend("",Constants.WORK_QUEUE,"hello spring amqp work ..."+i);

        }
        return "发送成功";
    }
    @RequestMapping("/fnout")
    public String fanout(){
rabbitTemplate.convertAndSend(Constants.FANOUT_EXCHANGE,"","hello spring amqp fanout...");
return "发送成功";
    }
}
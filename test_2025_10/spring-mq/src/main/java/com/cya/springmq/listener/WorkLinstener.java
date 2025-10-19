package com.cya.springmq.listener;

import com.cya.springmq.constant.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/19 22:59
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class WorkLinstener {

    @RabbitListener(queues = Constants.WORK_QUEUE)
    public void queueListener(Message message){
        System.out.println("listener1["+Constants.WORK_QUEUE+"]接收到消息："+message);
    }
    @RabbitListener(queues = Constants.WORK_QUEUE)
    public void queueListener2(String message){
        System.out.println("listener2["+Constants.WORK_QUEUE+"]接收到消息："+message);
    }
}
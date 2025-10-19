package com.cya.springmq.listener;

import com.cya.springmq.constant.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/20 0:47
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class FanoutListener {

    @RabbitListener(queues = Constants.FANOUT_QUEUE1)
    public void queueListener(String message){
        System.out.println("listener1["+Constants.FANOUT_QUEUE1+"]接收到消息："+message);
    }
    @RabbitListener(queues = Constants.FANOUT_QUEUE2)
    public void queueListener2(String message){
        System.out.println("listener2["+Constants.FANOUT_QUEUE2+"]接收到消息："+message);
    }
}
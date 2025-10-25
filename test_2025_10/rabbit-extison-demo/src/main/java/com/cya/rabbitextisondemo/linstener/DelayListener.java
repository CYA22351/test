package com.cya.rabbitextisondemo.linstener;

import com.cya.rabbitextisondemo.constant.Constants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/25 17:11
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class DelayListener {
@RabbitListener(queues = Constants.DELAY_QUEUE)
    public void delayMessage(Message message, Channel channel){
    System.out.printf("[dl:queue] %tc 接收到消息 %s\n",new Date(),new String(message.getBody()));
}
}
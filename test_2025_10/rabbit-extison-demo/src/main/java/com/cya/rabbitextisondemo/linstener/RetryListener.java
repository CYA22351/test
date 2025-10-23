package com.cya.rabbitextisondemo.linstener;

import com.cya.rabbitextisondemo.constant.Constants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/23 21:39
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class RetryListener {

    @RabbitListener(queues = Constants.RETRY_QUEUE)
    public void handMessage(Message message){
        System.out.printf("["+ Constants.RETRY_QUEUE+"]接收到消息 %s, deliverTag: %s\n",new String(message.getBody())
        ,message.getMessageProperties().getDeliveryTag());
        int num=3/0;
        System.out.println("发送成功");
    }
//    @RabbitListener(queues = Constants.RETRY_QUEUE)
//    public void handMessage(Message message, Channel channel) throws Exception {
//        System.out.printf("["+ Constants.RETRY_QUEUE+"]接收到消息 %s, deliverTag: %s\n",new String(message.getBody())
//                ,message.getMessageProperties().getDeliveryTag());
//try {
//    int num=3/0;
//
//    System.out.println("发送成功");
//    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//}catch (Exception e){
//    channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
//}
//    }
}
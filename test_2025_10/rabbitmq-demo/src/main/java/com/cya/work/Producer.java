package com.cya.work;

import com.cya.constant.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/18 11:58
 * @description：
 * @modified By：
 * @version:
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost(Constant.Host);
        connectionFactory.setPort(Constant.Port);
        connectionFactory.setUsername(Constant.USER_NAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        connectionFactory.setVirtualHost(Constant.VIRUAL_HOST);

        Connection connection=connectionFactory.newConnection();
//        开启信道
        Channel channel=connection.createChannel();
//        声明队列，使用内置交换机
//         如果队列不存在，则创建，如果存在，不创建
        channel.queueDeclare(Constant.WORK_QUEUE,true,false,false,null);
//        发送消息
        for (int i=0;i<10;i++){
            String msg="hello work_queue"+i;
            channel.basicPublish("",Constant.WORK_QUEUE,null,msg.getBytes());
        }
        System.out.println("消息发送成功");
        channel.close();
        connection.close();
    }
}
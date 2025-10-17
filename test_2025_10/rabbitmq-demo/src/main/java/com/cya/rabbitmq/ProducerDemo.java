package com.cya.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/17 21:02
 * @description：
 * @modified By：
 * @version:
 */
public class ProducerDemo {
    public static void main(String[] args) throws IOException, TimeoutException {
//       建立连接
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("39.97.40.226");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("study");
        connectionFactory.setPassword("study");
        connectionFactory.setVirtualHost("cya");
        Connection connection = connectionFactory.newConnection();
//        开启信道
        Channel channel=connection.createChannel();
//        声明交换机，使用内置交换机
//        声明队列
        channel.queueDeclare("hello",true,false,false,null);
// 发送消息
        String msg="hello rabbitma~";
        channel.basicPublish("","hello",null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
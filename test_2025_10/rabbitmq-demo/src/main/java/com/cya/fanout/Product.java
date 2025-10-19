package com.cya.fanout;

import com.cya.constant.Constant;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/18 15:54
 * @description：
 * @modified By：
 * @version:
 */
public class Product {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost(Constant.Host);
        connectionFactory.setPort(Constant.Port);
        connectionFactory.setUsername(Constant.USER_NAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        connectionFactory.setVirtualHost(Constant.VIRUAL_HOST);

        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();
//       声明交换机
        channel.exchangeDeclare(Constant.FANOUT_EXCHANGE, BuiltinExchangeType.FANOUT,true);
//        声明队列
        channel.queueDeclare(Constant.FANOUT_QUEUE1,true,false,false,null);
        channel.queueDeclare(Constant.FANOUT_QUEUE2,true,false,false,null);
//交换机和队列绑定
        channel.queueBind(Constant.FANOUT_QUEUE1,Constant.FANOUT_EXCHANGE,"");
        channel.queueBind(Constant.FANOUT_QUEUE2,Constant.FANOUT_EXCHANGE,"");
// 发送消息
        String msg="hello fanout...";
        channel.basicPublish(Constant.FANOUT_EXCHANGE,"",null,msg.getBytes());
        System.out.println("消息发送成功");
//关闭资源
        channel.close();
        connection.close();
    }
}
package com.cya.direct;

import com.cya.constant.Constant;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/18 16:26
 * @description：路由器模式
 * @modified By：
 * @version:
 */
public class Producer {
    public static void main(String[] args) throws TimeoutException, IOException {
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost(Constant.Host);
        connectionFactory.setPort(Constant.Port);
        connectionFactory.setUsername(Constant.USER_NAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        connectionFactory.setVirtualHost(Constant.VIRUAL_HOST);

        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();
//        声明交换机
        channel.exchangeDeclare(Constant.DIRECT_EXCHANGE,BuiltinExchangeType.DIRECT,true);
//        声明队列
        channel.queueDeclare(Constant.DIRECT_QUEUE1,true,false,false,null);
        channel.queueDeclare(Constant.DIRECT_QUEUE2,true,false,false,null);
//        绑定交换机和队列
        channel.queueBind(Constant.DIRECT_QUEUE1,Constant.DIRECT_EXCHANGE,"a");
        channel.queueBind(Constant.DIRECT_QUEUE2,Constant.DIRECT_EXCHANGE,"a");
        channel.queueBind(Constant.DIRECT_QUEUE2,Constant.DIRECT_EXCHANGE,"b");
        channel.queueBind(Constant.DIRECT_QUEUE2,Constant.DIRECT_EXCHANGE,"c");
//        发送消息
        String msg_a="hello direct ,my routingkey is a...";
        channel.basicPublish(Constant.DIRECT_EXCHANGE,"a",null,msg_a.getBytes());

        String msg_b="hello direct ,my routingkey is b...";
        channel.basicPublish(Constant.DIRECT_EXCHANGE,"b",null,msg_b.getBytes());

        String msg_c="hello direct ,my routingkey is c...";
        channel.basicPublish(Constant.DIRECT_EXCHANGE,"c",null,msg_c.getBytes());

        System.out.println("消息发送成功");
        channel.close();
        connection.close();
    }
}
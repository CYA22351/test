package com.cya.topic;

import com.cya.constant.Constant;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/18 17:38
 * @description：通配符模式
 * @modified By：
 * @version:
 */
public class Procuder {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost(Constant.Host);
        connectionFactory.setPort(Constant.Port);
        connectionFactory.setUsername(Constant.USER_NAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        connectionFactory.setVirtualHost(Constant.VIRUAL_HOST);

        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();
//        声明交换机
        channel.exchangeDeclare(Constant.TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC,true);
//        声明队列
        channel.queueDeclare(Constant.TOPIC_QUEUE1,true,false,false,null);
        channel.queueDeclare(Constant.TOPIC_QUEUE2,true,false,false,null);
//        绑定交换机和队列
        channel.queueBind(Constant.TOPIC_QUEUE1,Constant.TOPIC_EXCHANGE,"*.a.*");
        channel.queueBind(Constant.TOPIC_QUEUE2,Constant.TOPIC_EXCHANGE,"*.*.b");
        channel.queueBind(Constant.TOPIC_QUEUE2,Constant.TOPIC_EXCHANGE,"c.#");
//        发送消息
        String msg_a="hello topic ,my routingkey is ae.a.f...";
//        转发到queue1
        channel.basicPublish(Constant.TOPIC_EXCHANGE,"ae.a.f",null,msg_a.getBytes());

        String msg_b="hello topic ,my routingkey is ef.a.b...";
//        转发到q1和q2
        channel.basicPublish(Constant.TOPIC_EXCHANGE,"ef.a.b",null,msg_b.getBytes());

        String msg_c="hello topic ,my routingkey is c.ef.d...";
//        转发到q2
        channel.basicPublish(Constant.TOPIC_EXCHANGE,"c.ef.d",null,msg_c.getBytes());

        System.out.println("消息发送成功");
        channel.close();
        connection.close();
    }
}
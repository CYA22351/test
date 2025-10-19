package com.cya.fanout;

import com.cya.constant.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/18 16:08
 * @description：
 * @modified By：
 * @version:
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
//      创建连接，开启信道
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost(Constant.Host);
        connectionFactory.setPort(Constant.Port);
        connectionFactory.setUsername(Constant.USER_NAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        connectionFactory.setVirtualHost(Constant.VIRUAL_HOST);

        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(Constant.FANOUT_QUEUE2,true,false,false,null);
        //        消费消息
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//               TOOO
                System.out.println("接收到消息："+new String(body));
            }
        };
        channel.basicConsume(Constant.FANOUT_QUEUE2,true,consumer);

    }
}
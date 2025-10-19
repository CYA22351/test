package com.cya.work;

import com.cya.constant.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/18 15:33
 * @description：
 * @modified By：
 * @version:
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
//        创建按连接
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("39.97.40.226");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("study");
        connectionFactory.setPassword("study");
        connectionFactory.setVirtualHost("cya");
        Connection connection=connectionFactory.newConnection();

//        创建channel
        Channel channel=connection.createChannel();

//        声明队列 （可以省略）
        channel.queueDeclare(Constant.WORK_QUEUE,true,false,false,null);

//        消费消息
        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//               TOOO
                System.out.println("接收到消息："+new String(body));
            }
        };
        channel.basicConsume(Constant.WORK_QUEUE,true,consumer);
//
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        channel.close();
//        connection.close();
    }
}
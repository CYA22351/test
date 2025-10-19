package com.cya.rpc;

import com.cya.constant.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/19 16:27
 * @description：
 * @modified By：
 * @version:
 */
public class RPCServer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //        建立连接，开启信道
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Constant.Host);
        connectionFactory.setPort(Constant.Port);
        connectionFactory.setUsername(Constant.USER_NAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        connectionFactory.setVirtualHost(Constant.VIRUAL_HOST);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//
                String request=new String(body,"utf-8");
                System.out.println("接收到请求："+request);

                String  response="针对于请求： "+request+"，响应成功";
                AMQP.BasicProperties basicProperties=new AMQP.BasicProperties().builder()
                                .correlationId(properties.getCorrelationId())
                                        .build();
                channel.basicPublish("",Constant.RPC_RESPONSE_QUEUE,basicProperties,response.getBytes());
                channel.basicAck(envelope.getDeliveryTag(),false);
            }



        };
        channel.basicConsume(Constant.RPC_REQUEST_QUEUE,false,consumer);
    }}
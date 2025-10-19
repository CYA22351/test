package com.cya.rpc;

import com.cya.constant.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/19 15:38
 * @description： rpc ,发送请求，接受响应
 * @modified By：
 * @version:
 */
public class RpcClient {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//        建立连接，开启信道
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost(Constant.Host);
        connectionFactory.setPort(Constant.Port);
        connectionFactory.setUsername(Constant.USER_NAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        connectionFactory.setVirtualHost(Constant.VIRUAL_HOST);

        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(Constant.RPC_REQUEST_QUEUE,true,false,false,null);
        channel.queueDeclare(Constant.RPC_RESPONSE_QUEUE,true,false,false,null);

//        发送请求
//        设置请求的相关属性
        String msg="hello rpc...";
        String cprrelationID= UUID.randomUUID().toString();
        AMQP.BasicProperties props=new AMQP.BasicProperties().builder()
                        .correlationId(cprrelationID)
                        .replyTo(Constant.RPC_RESPONSE_QUEUE)
                        .build();
        channel.basicPublish("",Constant.RPC_REQUEST_QUEUE,props,msg.getBytes());
//        接受响应
//          使用阻塞队列来存储相应信息
        final BlockingQueue<String> response=new ArrayBlockingQueue<>(1);

        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//               TOOO
                String respMsg=new String(body);
                System.out.println("接收到回调消息："+respMsg);
                if (cprrelationID.equals(properties.getCorrelationId())){
                    response.offer(respMsg);
                }
            }
        };
        channel.basicConsume(Constant.RPC_RESPONSE_QUEUE,true,consumer);
    String result=response.take();
        System.out.println("[RPC Client响应结果]:"+result);
    }
}
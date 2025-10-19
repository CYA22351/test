package com.cya.publisher.confirms;

import com.cya.constant.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/19 17:27
 * @description：
 * @modified By：
 * @version:
 */
public class PublisherConfirms {
    public static final int MESSAGE_COUNT=10000;

    static Connection createConnenction() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost(Constant.Host);
        connectionFactory.setPort(Constant.Port);
        connectionFactory.setUsername(Constant.USER_NAME);
        connectionFactory.setPassword(Constant.PASSWORD);
        connectionFactory.setVirtualHost(Constant.VIRUAL_HOST);

        return connectionFactory.newConnection();
    }


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//        单独确认
//        publishingMessagesIndividuolly();
//        批量确认
        publishMessagesInBatch();
//        异步确认
        handlePublishConfirmsAsynchronously();
    }

    private static void handlePublishConfirmsAsynchronously() throws IOException, TimeoutException, InterruptedException {
        try (Connection connection=createConnenction()){
            Channel channel=connection.createChannel();
            channel.confirmSelect();
//            声明队列
            channel.queueDeclare(Constant.PUBLISHER_CONFIRMS_QUEUE3,true,false,false,null);
//            监听confirms
            SortedSet<Long> confirmSeqNo= Collections.synchronizedSortedSet(new TreeSet<>());
            long start=System.currentTimeMillis();
            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long l, boolean b) throws IOException {
                    if (b){
                        confirmSeqNo.headSet(l+1).clear();

                    }
                    else {
                        confirmSeqNo.headSet(l);
                    }
                }

                @Override
                public void handleNack(long l, boolean b) throws IOException {
if (b){
    confirmSeqNo.headSet(l+1).clear();
}else {
    confirmSeqNo.remove(l);
}
                }
            });
            for (int i=0 ;i<MESSAGE_COUNT;i++){
                String msg="hello publisher confirms"+i;
                long seqNo=channel.getNextPublishSeqNo();
                channel.basicPublish("",Constant.PUBLISHER_CONFIRMS_QUEUE3,null,msg.getBytes());
                confirmSeqNo.add(seqNo);
            }
            while (!confirmSeqNo.isEmpty()){
                Thread.sleep(10);
            }
            long end=System.currentTimeMillis();
            System.out.printf("异步确认策略，消息条数：%d 耗时：%d ms\n",MESSAGE_COUNT,end-start);

        }
    }


    private static void publishMessagesInBatch() throws IOException, TimeoutException, InterruptedException {
        try (Connection connection=createConnenction()){
//            开启信道
            Channel channel=connection.createChannel();
//            设置信道为confirms模式
            channel.confirmSelect();
//            声明队列
            //            声明队列
            channel.queueDeclare(Constant.PUBLISHER_CONFIRMS_QUEUE2,true,false,false,null);
//            发送消息，并进行确认
            long statr=System.currentTimeMillis();
            int batchSize=100;
            int outstangingMessageCount=0;
            for (int i=0 ;i<MESSAGE_COUNT;i++){
                String msg="hello publisher confirms"+i;
                channel.basicPublish("",Constant.PUBLISHER_CONFIRMS_QUEUE2,null,msg.getBytes());
                outstangingMessageCount++;
                if (outstangingMessageCount==batchSize){
                    channel.waitForConfirmsOrDie(5000);
                    outstangingMessageCount=0;
                }
                }
            if (outstangingMessageCount>0){
                channel.waitForConfirmsOrDie(5000);
            }
            long end=System.currentTimeMillis();
            System.out.printf("批量确认策略，消息条数：%d 耗时：%d ms\n",MESSAGE_COUNT,end-statr);


        }
    }

    private static void publishingMessagesIndividuolly() throws IOException, TimeoutException, InterruptedException {
        try (Connection connection=createConnenction()){
//            开启信道
            Channel channel=connection.createChannel();
//            设置信达为confirm模式
            channel.confirmSelect();
//            声明队列
            channel.queueDeclare(Constant.PUBLISHER_CONFIRMS_QUEUE1,true,false,false,null);
            long start=System.currentTimeMillis();
            for (int i=0 ;i<MESSAGE_COUNT;i++){
            String msg="hello publisher confirms"+i;
            channel.basicPublish("",Constant.PUBLISHER_CONFIRMS_QUEUE1,null,msg.getBytes());
//            等待确认
                channel.waitForConfirmsOrDie(5000);
            }
            long end=System.currentTimeMillis();
            System.out.printf("单独确认策略，消息条数：%d 耗时：%d ms\n",MESSAGE_COUNT,end-start);
        }

    }
}
package com.cya.logisticsservice.linstener;

import com.cya.orderservice.model.OrderInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/21 17:17
 * @description：
 * @modified By：
 * @version:
 */
@Component
@RabbitListener(queues = "order.create")

public class OrderLinstener {
//
//@RabbitHandler
//    public void handMessage(String orderInfo){
//        System.out.println("String接收到订单信息："+orderInfo);
//    }
  @RabbitHandler
    public void handMessage(OrderInfo orderInfo){
        System.out.println("OrderInfo接收到订单信息："+orderInfo);
    }
}
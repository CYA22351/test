package com.cya.orderservice.controller;

import com.cya.orderservice.model.OrderInfo;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/21 16:59
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/create")
    public String create(){

            String orderID= UUID.randomUUID().toString();

        rabbitTemplate.convertAndSend("","order.create","订单信息,订单Id:"+orderID);

        return "下单成功";
    }
    @RequestMapping("/create2")
    public String create2(){

       OrderInfo orderInfo=new OrderInfo();

        orderInfo.setOrderId(UUID.randomUUID().toString());

        orderInfo.setName("商品"+new Random().nextInt(100));

        rabbitTemplate.convertAndSend("","order.create",orderInfo);

        return "下单成功";
    }
}
package com.cya.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/21 17:28
 * @description：
 * @modified By：
 * @version:
 */

@Data
public class OrderInfo   {
    public String orderId;
    public String name;

    public static void main(String[] args) {
        OrderInfo orderInfo  = new OrderInfo();
        orderInfo.getOrderId();
    }
}
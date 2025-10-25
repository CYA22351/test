package com.cya.rabbitextisondemo.constant;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/23 15:41
 * @description：
 * @modified By：
 * @version:
 */
public class Constants {
        public static final String ACK_QUEUE="ack_queue";
    public static final String ACK_EXCHANGE="ack_exchange";
    public static final String PRES_QUEUE="pres_queue";
    public static final String PRES_EXCHANGE="pres_queue";

    //    发送确认
    public static String CONFIRM_QUEUE="confirm_queue";
    public static String CONFIRM_EXCHANGE="confirm_exchange";
//重试机制
    public static final String RETRY_QUEUE="retry_queue";
    public static final String RETRY_EXCHANGE="retry_exchange";

//    延迟队列
    public static final String DELAY_QUEUE="delay_queue";
    public static final String DELAY_EXCHANGE="delay_sxchange";

//    事务
    public static final String TRANS_QUEUE="trans_queue";
}
package com.cya.constant;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/18 11:59
 * @description：
 * @modified By：
 * @version:
 */
public class Constant {
    public static final String Host="39.97.40.226";
    public static final int Port=5672;
    public static final String USER_NAME="study";
    public static final String PASSWORD="study";

    public static final String VIRUAL_HOST="cya";
    public static final String WORK_QUEUE="work_queue";
    public static final String FANOUT_EXCHANGE="fanout_exchange";
    public static final String FANOUT_QUEUE1="fanout_queue1";
    public static final String FANOUT_QUEUE2="fanout_queue2";

//    路由模式
    public static final String DIRECT_EXCHANGE="direct_exchange_new";
    public static final String DIRECT_QUEUE1="direct_queue1";
    public static final String DIRECT_QUEUE2="direct_queue2";

//    通配符模式
    public static final String TOPIC_EXCHANGE="topic_exchange";
    public static final String TOPIC_QUEUE1="topic_queue1";
    public static final String TOPIC_QUEUE2="topic_queue2";

//    rpc
    public static final String RPC_REQUEST_QUEUE="rpc.request.queue";
    public static final String RPC_RESPONSE_QUEUE="rpc_response.queue";
//    publisher confirms
    public static final String PUBLISHER_CONFIRMS_QUEUE1="publisher_confirms_queue1";
    public static final String PUBLISHER_CONFIRMS_QUEUE2="publisher_confirms_queue2";
    public static final String PUBLISHER_CONFIRMS_QUEUE3="publisher_confirms_queue3";

}
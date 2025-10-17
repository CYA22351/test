package com.cya;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;


/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/17 10:05
 * @description：
 * @modified By：
 * @version:
 */
public class RedisDemoString {
    public static void main(String[] args) {
        JedisPool jedisPool=new JedisPool("tcp://127.0.0.1:8888");
        try(Jedis jedis=jedisPool.getResource();) {
            test(jedis);
        }
    }

    private static void test(Jedis jedis) {
        System.out.println("mset和mget");
         jedis.mset("key1","111","key2","222","key3","333");
        List<String> values = jedis.mget("key", "key2", "key3");
        System.out.println("value: "+values);
    }
}
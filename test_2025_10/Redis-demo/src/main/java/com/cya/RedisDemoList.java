package com.cya;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/17 10:29
 * @description：
 * @modified By：
 * @version:
 */
public class RedisDemoList {
    public static void main(String[] args) {
        JedisPool jedisPool=new JedisPool("tcp://127.0.0.1:8888");
        try (Jedis jedis=jedisPool.getResource()){
            test2(jedis);
        }
    }
public static void test2(Jedis jedis){
    System.out.println("rpush");
    jedis.flushAll();
    jedis.rpush("key", "111", "222", "333");
    List<String> key = jedis.lrange("key", 0, -1);
    System.out.println("values: "+key);
}
    private static void test1(Jedis jedis) {
        System.out.println("lpush和lrange");
        jedis.flushAll();
        jedis.lpush("key","111","222","333");
        List<String> key = jedis.lrange("key", 0, -1);
        System.out.println("value: "+key);
    }
}
package com.cya;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.Tuple;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/17 14:55
 * @description：
 * @modified By：
 * @version:
 */
public class RedisDemoZset {
    public static void main(String[] args) {
        JedisPool jedisPool=new JedisPool("tcp://127.0.0.1:8888");
        try (Jedis jedis=jedisPool.getResource()){
            test(jedis);
        }
    }

    private static void test(Jedis jedis) {
        System.out.println("zadd 和 zrange");
        jedis.flushAll();
        jedis.zadd("key",10,"张三");
        Map<String, Double> map=new HashMap<>();
        map.put("lisi",20.0);
        map.put("wamgwu",30.0);
        jedis.zadd("key",map);

        List<String> membenrs = jedis.zrange("key", 0, -1);
        System.out.println("members= "+membenrs);

        List<Tuple> memberswithscores = jedis.zrangeWithScores("key", 0, -1);
        System.out.println("memberwithscores="+memberswithscores);
        String member = memberswithscores.get(0).getElement();
        double score = memberswithscores.get(0).getScore();
        System.out.println("member: "+member+",scores= "+score);
    }
}
package com.cya;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentineled;
import redis.clients.jedis.params.Params;
import redis.clients.jedis.params.SetParams;

import java.util.Set;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/16 21:45
 * @description：
 * @modified By：
 * @version:
 */
public class RedisDemo {
    public static  void test1 (Jedis jedis){
        System.out.println("get  和set使用");
//        q清空数据库
        jedis.flushAll();
        jedis.set("key","111");
        jedis.set("key2","222");
        SetParams params=new SetParams();
        params.ex(10);
        params.xx();
        jedis.set("key","333");
        String value = jedis.get("key");
        System.out.println("value="+value);
    }

    public  static  void test2(Jedis jedis){
        System.out.println("exist和del");
        jedis.flushAll();
        jedis.set("key","111");
        jedis.set("key","222");
        boolean key = jedis.exists("key");
        System.out.println("result="+key);
        long key1 = jedis.del("key");
        System.out.println("result2="+key1);

        boolean key2 = jedis.exists("key");
        System.out.println("result:"+key2);

    }

    public static void test3(Jedis jedis){
        System.out.println("keys");
        jedis.flushAll();
        jedis.set("key","111");
        jedis.set("key2","111");
        jedis.set("key3","111");
        jedis.set("key4","111");
        Set<String> keys =  jedis.keys("*");
        System.out.println(keys);
    }
    public static void test4(Jedis jedis){
        System.out.println("设置key过期时间");
        jedis.flushAll();
        jedis.set("key","111");
        jedis.expire("key",10);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long key = jedis.ttl("key");
        System.out.println("time= "+key );
    }
    public static void test5(Jedis jedis){
        System.out.println("type");
        jedis.flushAll();
        jedis.set("key","111");
        String ty = jedis.type("key");
        System.out.println("type= "+ty);
        jedis.lpush("key2","111","222","333");
        String key2 = jedis.type("key2");
        System.out.println("type2= "+key2);

        jedis.hset("key3","f1","111");
        ty=jedis.type("key3");
        System.out.println("type= "+ty);
    }
    public static void main(String[] args) {
        JedisPool jedisPool=new JedisPool("tcp://127.0.0.1:8888");
//       从redis连接池取出一个连接
        try (Jedis jedis=jedisPool.getResource()){
//            String pong = jedis.ping();
//            System.out.println(pong);
            test5(jedis);
        }
    }
}
package com.cya.springdemoredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/17 15:35
 * @description：
 * @modified By：
 * @version:
 */
@RestController
public class MyController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/testString")
    @ResponseBody
    public String testString(){
        redisTemplate.execute((RedisConnection connection)->{
            connection.flushAll();
            return null;
        });
        redisTemplate.opsForValue().set("key","111");
        redisTemplate.opsForValue().set("key2","111");
        redisTemplate.opsForValue().set("key3","111");
        String value = redisTemplate.opsForValue().get("key");

        System.out.println("value: "+value);

return "ok";
    }
    @GetMapping("/testList")
    @ResponseBody
    public String testList()
    {
        redisTemplate.execute((RedisConnection connection)->{
            connection.flushAll();
            return null;
        });
        redisTemplate.opsForList().leftPush("key","111");
        redisTemplate.opsForList().leftPush("key","222");
        redisTemplate.opsForList().leftPush("key","333");
        String value = redisTemplate.opsForList().rightPop("key");
        System.out.println("value: "+value);
         value = redisTemplate.opsForList().rightPop("key");
        System.out.println("value: "+value);
        value = redisTemplate.opsForList().rightPop("key");
        System.out.println("value: "+value);

        return "ok" ;
    }
    @GetMapping("/testSet")
    @ResponseBody
    public String testSet(){
        //清空数据库
        redisTemplate.execute((RedisConnection connection)->{
            connection.flushAll();
            return null;
        });
        redisTemplate.opsForSet().add("key","111","222","333");
        Set<String> result = redisTemplate.opsForSet().members("key");
        System.out.println("result: "+result);

        Boolean exists = redisTemplate.opsForSet().isMember("key", "111");
        System.out.println("exist: "+exists);

        Long count = redisTemplate.opsForSet().size("key");
        System.out.println("count: "+count);

        redisTemplate.opsForSet().remove("key","111","222");

        result= redisTemplate.opsForSet().members("key");
        System.out.println("result: "+result);

        return "ok";
    }

    @GetMapping("/testHash")
    @ResponseBody
    public String testHash(){
        //清空数据库
        redisTemplate.execute((RedisConnection connection)->{
            connection.flushAll();
            return null;
        });

        redisTemplate.opsForHash().put("key","f1","111");
        redisTemplate.opsForHash().put("key","f2","222");
        redisTemplate.opsForHash().put("key","f3","333");
        String value = (String) redisTemplate.opsForHash().get("key", "f1");
        System.out.println("value: "+value);

        Boolean exist = redisTemplate.opsForHash().hasKey("key", "f1");

        System.out.println("exist: "+exist);

        redisTemplate.opsForHash().delete("key","f1");

        Long count = redisTemplate.opsForHash().size("key");

        System.out.println("count: "+count);

        return "ok";
    }
    @GetMapping("/testZset")
    @ResponseBody
    public String testZset()
    {
        //清空数据库
        redisTemplate.execute((RedisConnection connection)->{
            connection.flushAll();
            return null;
        });
        
        redisTemplate.opsForZSet().add("key","zhangsan",10);
        redisTemplate.opsForZSet().add("key","lisi",20);
        redisTemplate.opsForZSet().add("key","wangwu",30);
        Set<String > member = redisTemplate.opsForZSet().range("key", 0, -1);
        System.out.println("members: "+member);
        Set<ZSetOperations.TypedTuple<String>> memberwithscores = redisTemplate.opsForZSet().rangeWithScores("key", 0, -1);
        System.out.println("memberwithscores: "+memberwithscores );

        Double score = redisTemplate.opsForZSet().score("key", "zhangsan");
        System.out.println("score:"+score);
        redisTemplate.opsForZSet().remove("key","lisi");
        Long size = redisTemplate.opsForZSet().size("key");
        System.out.println("size: "+size);
        Long rank = redisTemplate.opsForZSet().rank("key", "wangwu");
        System.out.println("rank :"+rank);
        return "ok";
    }
}
package com.cya.springbootdemo2;

import com.cya.springbootdemo2.modle.userInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/8 14:20
 * @description：
 * @modified By：
 * @version:
 */
public class JsonTest {
    //测试注解,类似main方法
    //json实际上是一串字符串
   @Test
   //对象转json
    void testJson() throws JsonProcessingException {
       ObjectMapper objectMapper=new ObjectMapper();
       //创建java对象
       userInfo userInfo=new userInfo("zhangsan",15,1);

       //对象转json
       String s=objectMapper.writeValueAsString(userInfo);
       System.out.println(s);

   }
    @Test
        //json转对象
    void testJsontoObject() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        //定义json字符串
        String s="{\"name\":\"zhangsan\",\"gender\":1,\"age\":15}";
        //json转对象
        //先创建一个空对象，必须要有空构造方法
        userInfo userInfo = objectMapper.readValue(s, userInfo.class);
        System.out.println(userInfo.toString());
    }

}
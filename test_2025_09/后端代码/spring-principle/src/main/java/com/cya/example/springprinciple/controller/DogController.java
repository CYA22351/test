package com.cya.example.springprinciple.controller;

import com.cya.example.springprinciple.model.Dog;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/17 16:10
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/dog")
public class DogController {

    @Autowired
    ApplicationContext context;



    @Resource(name = "singleDog")
    Dog singleDog;

    @Resource(name = "prototypeDog")
    Dog prototypeDog;

    @Resource(name = "requestDog")
    Dog requestDog;

    @Resource(name = "sessionDog")
    Dog sessionDog;

    @Resource(name = "applicationDog")
    Dog applicationDog;
    @RequestMapping("/singleton")
    public String singleton(){
        Dog bean = context.getBean("singleDog", Dog.class);

        return "contecDog:"+bean+"resource:"+singleDog;
    }

    //原型作用域
    @RequestMapping("/prototy")
    public String prototy(){

        Dog bean = context.getBean("prototypeDog", Dog.class);

        return "contecDog:"+bean+", resource:"+prototypeDog;
    }

    @RequestMapping("/request")
    public String request(){

        Dog bean = context.getBean("requestDog", Dog.class);

        return "contecDog:"+bean+", resource:"+requestDog;
    }

    @RequestMapping("/session")
    public String session(){

        Dog bean = context.getBean("sessionDog", Dog.class);

        return "contecDog:"+bean+", resource:"+sessionDog;
    }

    @RequestMapping("/application")
    public String application(){

        Dog bean = context.getBean("applicationDog", Dog.class);

        return "contecDog:"+bean+", resource:"+applicationDog;
    }
}
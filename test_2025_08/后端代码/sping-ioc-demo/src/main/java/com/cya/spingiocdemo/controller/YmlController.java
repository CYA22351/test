package com.cya.spingiocdemo.controller;

import com.cya.spingiocdemo.model.DbTypes;
import com.cya.spingiocdemo.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/17 19:44
 * @description：
 * @modified By：
 * @version:
 */
@Controller
public class YmlController {

    @Value("${my.key3}")
    private String mykey3;

    @Value("${my.key4}")
    private String mykey4;

    @Autowired
    private Person person;

    @Autowired
    private DbTypes dbTypes;

    @PostConstruct//初始化方法,spring创建该对象放入容器中就要执行该方法，
    public void init(){
        System.out.println("mykey3:"+mykey3);
        System.out.println("mykey4:"+mykey4);
        System.out.println("person: "+person);
        System.out.println("dbTypes: "+dbTypes);

    }

}
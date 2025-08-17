package com.cya.spingiocdemo.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/17 16:41
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("prop")
@ResponseBody
@Controller
public class PropertiesController {
    @Value("${my.key1}")
    private String mykey;

    @Value("${my.key2}")
    private boolean mykey2;
    @RequestMapping("read")
    public String readvalue(){
        return "读取配置文件"+mykey;
    }
    @PostConstruct//初始化方法
    public void init(){
        System.out.println("读取配置文件"+mykey2);
    }
}
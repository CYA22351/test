package com.cya.springbootdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/7 8:59
 * @description：
 * @modified By：
 * @version:
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello,spring boot";
    }
}
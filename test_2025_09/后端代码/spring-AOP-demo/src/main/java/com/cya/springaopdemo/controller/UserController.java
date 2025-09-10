package com.cya.springaopdemo.controller;

import com.cya.springaopdemo.aspect.MyAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/9 17:25
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {


@RequestMapping("/U1")
    public String U1(){
    System.out.println("执行u1");
        return "u1";
    }
@MyAspect
    @RequestMapping("/u2")
    public String u2(){
        System.out.println("执行u2");
        return "u2";
    }
}
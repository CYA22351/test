package com.cya.spingiocdemo.controller;

import org.springframework.stereotype.Controller;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/14 10:29
 * @description：
 * @modified By：
 * @version:
 */
@Controller//将对象交给spring
public class HelloController {
    public void print(){
        System.out.println("do Controller");
    }
}
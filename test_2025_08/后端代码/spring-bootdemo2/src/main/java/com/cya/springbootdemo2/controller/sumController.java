package com.cya.springbootdemo2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/10 9:31
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("sum")
@RestController
public class sumController {
    @RequestMapping("r1")
    public String sum(Integer num1,Integer num2){
        Integer sum=num1+num2;
        return "<h1>计算结果："+sum+"</h1>";
    }
}
package com.cya.springaopdemo.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/8 14:44
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {
    @RequestMapping("/t1")
    public Integer t1(){

      log.info("执行t1");
      int a=10/0;
        return 1;
    }

    @RequestMapping("/t2")
    public boolean t2(){
        System.out.println("执行t2");
        return true;
    }
    @RequestMapping("/t3")
    public String t3(){

        System.out.println("执行t3");
        return "t3";
    }
}
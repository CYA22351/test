package com.cya.springlogdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/18 23:44
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("/log")
@RestController
public class LogController {

    private final static Logger logger= LoggerFactory.getLogger(LogController.class);//定义日志对象

    @RequestMapping("/print")
    public String print(){
        System.out.println("sout打印日志");
        logger.info("logger 打印日志");
        return "打印日志";
    }
}
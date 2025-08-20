package com.cya.springlogdemo.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j//自动生成log对象 q
@RequestMapping("/log")
@RestController
public class LogController {

   // private final static Logger logger= LoggerFactory.getLogger(LogController.class);//定义日志对象

    @RequestMapping("/print")
    public String print(){
        System.out.println("sout打印日志");
        log.info("logger 打印日志");
        log.trace("trace 打印日志");
        log.debug("debug 打印日志");
        log.warn("warn 打印日志");
        log.error("error 打印日志");
        return "打印日志";
    }
}
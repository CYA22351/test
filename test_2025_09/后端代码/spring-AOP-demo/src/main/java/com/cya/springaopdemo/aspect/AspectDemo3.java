package com.cya.springaopdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/9 20:08
 * @description：
 * @modified By：
 * @version:
 */
@Aspect
@Component
@Slf4j
@Order(2)
public class AspectDemo3 {

//    @Pointcut("execution(* com.cya.springaopdemo.controller.*.*(..))")
//    public void pt2(){};

    @Before("execution(* com.cya.springaopdemo.controller.*.*(..))")
    public void doBefore(){
        log.info("AspectDemo2 doBefore...");

    }
    @After("execution(* com.cya.springaopdemo.controller.*.*(..))")
    public void doAfter(){
        log.info("AspectDemo2 doAfter...");

    }
}
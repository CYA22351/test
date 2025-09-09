package com.cya.springaopdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/9 17:29
 * @description：
 * @modified By：
 * @version:
 */
@Aspect
//@Component
@Slf4j
public class AspectDemo1 {

    @Pointcut("execution(* com.cya.springaopdemo.controller.*.*(..))")
    public void pt(){};

//ProceedingJoinPoint pjp只能在Around中使用
@Around("pt()")
    public Object recordTime(ProceedingJoinPoint pjp)  {
    log.info("目标方法执行前");
        long begin=System.currentTimeMillis();
    Object result= null;
    try {
        result = pjp.proceed();
    } catch (Throwable e) {
        log.info("do Around throwing...");
    }
    long end=System.currentTimeMillis();
        log.info(pjp.getSignature()+"执行耗时：{}ms",end-begin);
        log.info("目标方法执行后");
        return result;
    }

    @Before("pt()")
    public void doBefore(){
    log.info("doBefore...");

    }
    @After("pt()")
    public void doAfter(){
    log.info("doAfter...");

    }
    @AfterReturning("pt()")
    public void doAfterReturning(){
    log.info("doAfterReturning...");
    }

    @AfterThrowing("pt()")
    public void doAfterThrowing(){
    log.info("doAfterThrowing...");

    }

}
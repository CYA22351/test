package com.cya.springaopdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/10 18:05
 * @description：
 * @modified By：
 * @version:
 */
@Aspect
@Component
@Slf4j
public class MyAspectDemo {

    @Around("@annotation(com.cya.springaopdemo.aspect.MyAspect)")
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
}
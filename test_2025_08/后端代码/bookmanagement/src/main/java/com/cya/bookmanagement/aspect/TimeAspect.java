package com.cya.bookmanagement.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/9 11:24
 * @description：
 * @modified By：
 * @version:
 */
@Component
@Aspect
@Slf4j
public class TimeAspect {
    @Around("execution(* com.cya.bookmanagement.Controller.*.*(..))")
    public Object timeRecord(ProceedingJoinPoint pjp) throws Throwable {
        long start=System.currentTimeMillis();
        Object proceed=pjp.proceed();
        long end=System.currentTimeMillis();
        log.info(pjp.getSignature().toString()+"耗时:"+(end-start)+"ms");
        return proceed;
    }
}
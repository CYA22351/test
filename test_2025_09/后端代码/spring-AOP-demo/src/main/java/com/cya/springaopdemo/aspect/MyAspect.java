package com.cya.springaopdemo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/10 14:12
 * @description：
 * @modified By：
 * @version:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAspect {

}
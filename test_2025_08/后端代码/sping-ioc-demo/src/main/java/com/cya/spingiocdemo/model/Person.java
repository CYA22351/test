package com.cya.spingiocdemo.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/17 19:56
 * @description：
 * @modified By：
 * @version:
 */
@ConfigurationProperties(prefix = "person")//从配置文件中获取person对象与当前对象匹配赋值
@Configuration
@Data
public class Person {
    private Integer id;
    private String name;
    private Integer age;
}
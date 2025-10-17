package com.cya.forumproject.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/10/9 16:09
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
// 指定Mybatis的扫描路径
@MapperScan("com.cya.forumproject.dao")
public class MybatisConfig {
}

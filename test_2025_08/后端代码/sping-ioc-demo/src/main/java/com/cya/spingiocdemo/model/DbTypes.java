package com.cya.spingiocdemo.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/18 0:11
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "dbtypes")
public class DbTypes {
    private List<String> name;
    private Map<String,String> ball;

}
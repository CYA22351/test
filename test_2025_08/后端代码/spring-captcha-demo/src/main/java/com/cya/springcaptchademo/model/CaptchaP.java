package com.cya.springcaptchademo.model;

import jakarta.websocket.Session;
import lombok.Data;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/18 11:23
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "captcha")
public class CaptchaP {
    private Integer width;
    private Integer heigh;
    private Session session;

    @Data
    public static class Session{
        private String key;
        private String date;
    }
}
package com.cya.springblogdemo.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/13 20:01
 * @description：
 * @modified By：
 * @version:
 */
@AllArgsConstructor
@Data
public class UserLoginResponse {
    private Integer userId;
    private String token;
}
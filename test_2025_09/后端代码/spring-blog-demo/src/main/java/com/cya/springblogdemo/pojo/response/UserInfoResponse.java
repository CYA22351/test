package com.cya.springblogdemo.pojo.response;

import lombok.Data;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/14 15:53
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class UserInfoResponse {
    private Integer id;
    private String userName;
    private String githubUrl;
}
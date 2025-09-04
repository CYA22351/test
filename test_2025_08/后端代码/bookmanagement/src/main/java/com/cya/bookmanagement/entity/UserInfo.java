package com.cya.bookmanagement.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/3 23:32
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class UserInfo {
    private Integer id;
    private String userName;
    private String password;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
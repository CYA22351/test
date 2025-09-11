package com.cya.springtransdemo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/11 15:35
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class LogInfo {
    private Integer id;
    private String userName;
    private String op;
    private Date createTime;
    private Date updateTime;
}
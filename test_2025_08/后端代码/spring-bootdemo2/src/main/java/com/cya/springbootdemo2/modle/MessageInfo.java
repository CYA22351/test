package com.cya.springbootdemo2.modle;

import lombok.Data;

import java.util.Date;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/10 18:42
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class MessageInfo {
    private Integer id;
    private String from;
    private String to;
    private String message;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;


}
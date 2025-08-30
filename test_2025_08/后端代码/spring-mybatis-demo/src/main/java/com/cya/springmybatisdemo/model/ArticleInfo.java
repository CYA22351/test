package com.cya.springmybatisdemo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/30 17:14
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class ArticleInfo {
    private Integer id;
    private String title;
    private String content;
    private Integer uid;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
    private String username;
    private Integer age;
}
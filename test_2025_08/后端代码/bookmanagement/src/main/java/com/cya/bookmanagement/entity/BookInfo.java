package com.cya.bookmanagement.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/12 11:59
 * @description：
 * @modified By：
 * @version:
 */

@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer count;
    private BigDecimal price;
    private String publish;
    private Integer status;
    private String statusCN;
    private Date createTime;
    private Date updateTime;
}
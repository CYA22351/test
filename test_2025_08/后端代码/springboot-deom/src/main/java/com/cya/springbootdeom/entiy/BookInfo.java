package com.cya.springbootdeom.entiy;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/15 21:51
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class BookInfo {
    private Integer bookId;
    private String bookName;
    private String author;
    private Integer num;
    private BigDecimal price;
    private String publish;
    private Integer status;
    private String statuscn;

}
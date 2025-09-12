package com.cya.springblogdemo.pojo.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 18:17
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class BlogInfoResopnse {

    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    @JsonFormat(pattern = "yyyy年mm月dd日")
    private LocalDateTime createTime;

//    public String getContent() {
//        return content==null?"":content.substring(0,50);
//    }
}
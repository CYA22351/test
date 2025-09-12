package com.cya.springblogdemo.pojo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 17:11
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class UserInfo {
    @TableId(type = IdType.AUTO)//自增id
    private Integer id;
    private String userName;
    private String password;
    private String githubUrl;
    private Integer deleteFlag;
    private LocalDate createTime;
    private LocalDate updateTime;
}
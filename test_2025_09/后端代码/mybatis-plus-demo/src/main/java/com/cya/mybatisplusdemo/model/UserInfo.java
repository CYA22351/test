package com.cya.mybatisplusdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;

import java.util.Date;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/5 17:30
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class UserInfo
{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer gender;
    private String phone;
//    @TableField("delete_flag")
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
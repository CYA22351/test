package com.cya.springblogdemo.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/13 20:03
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class UserLoginRequest {
    @NotNull(message = "用户名不能为空")
    @Length(max = 20,min = 2,message = "用户名长度不规范")
    private String userName;
    @NotNull(message = "密码不能为空")
    private String password;
}
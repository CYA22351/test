package com.cya.springblogdemo.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/14 18:08
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class AddBlogRequest {
    @NotNull(message = "userId不能为null")
    private Integer userId;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "内容不能为空")
    private String content;
}
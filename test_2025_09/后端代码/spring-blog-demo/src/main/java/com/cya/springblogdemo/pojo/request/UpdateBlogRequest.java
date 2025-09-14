package com.cya.springblogdemo.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/14 20:24
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class UpdateBlogRequest {
    @NotNull(message = "id不能为空")
    private Integer id;
    private String title;
    private String content;
}
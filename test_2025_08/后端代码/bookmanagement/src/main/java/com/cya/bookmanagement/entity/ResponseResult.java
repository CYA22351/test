package com.cya.bookmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/4 17:46
 * @description：
 * @modified By：
 * @version:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResult<T> {
    private Integer total;
    private List<T> records;
    private PageRequest pageRequest;


}
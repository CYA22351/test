package com.cya.bookmanagement.entity;

import lombok.Data;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/4 17:49
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class PageRequest {
    //默认值
   private Integer currentPage=1;
   private Integer pageSize=10;
   private Integer offset;
   public Integer getOffset(){
       return (currentPage-1)*pageSize;
   }
}
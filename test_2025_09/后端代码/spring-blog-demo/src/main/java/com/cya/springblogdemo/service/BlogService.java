package com.cya.springblogdemo.service;

import com.cya.springblogdemo.pojo.response.BlogInfoResopnse;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 18:04
 * @description：
 * @modified By：
 * @version:
 */
public interface BlogService {
    List<BlogInfoResopnse> getList();

    BlogInfoResopnse getBlogDetail(Integer blogId);
}
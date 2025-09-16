package com.cya.springblogdemo.service;

import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.request.AddBlogRequest;
import com.cya.springblogdemo.pojo.request.UpdateBlogRequest;
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

    BlogInfo getBlogInfo(Integer blogId);

    boolean addBlog(AddBlogRequest addBlogRequest);

    boolean updateBlog(UpdateBlogRequest updateBlogRequest);

    boolean delete(Integer blogId);

    Integer CountBlog();
}
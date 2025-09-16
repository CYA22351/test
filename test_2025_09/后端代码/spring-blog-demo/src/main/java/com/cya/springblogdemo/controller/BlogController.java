package com.cya.springblogdemo.controller;

import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.request.AddBlogRequest;
import com.cya.springblogdemo.pojo.request.UpdateBlogRequest;
import com.cya.springblogdemo.pojo.response.BlogInfoResopnse;
import com.cya.springblogdemo.pojo.response.Result;
import com.cya.springblogdemo.service.BlogService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 18:00
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource(name = "blogServiceImpl")
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogInfoResopnse> getList(){
        log.info("获取博客列表");
       List<BlogInfoResopnse> blogInfos= blogService.getList();
       return blogInfos;
    }

    /**
     * 获取博客详情
     * @param blogId
     * @return 博客详情
     */
    @RequestMapping("/getBlogDetail")
    public BlogInfoResopnse getBlogDetail(@NotNull Integer blogId){

        log.info("获取博客详情，blogId：{}",blogId);
        return blogService.getBlogDetail(blogId);
    }

    /**
     * 添加博客
     */
    @RequestMapping("/addBlog")
    public boolean addBlog(@RequestBody @Validated AddBlogRequest addBlogRequest){
     log.info("发布博客,userId:{},title:{}",addBlogRequest.getUserId(),addBlogRequest.getTitle());
     return blogService.addBlog(addBlogRequest);
    }

    /**
     * 编辑博客
     */
    @RequestMapping("/update")
    public boolean update(@RequestBody @Validated UpdateBlogRequest updateBlogRequest){
        log.info("更新博客,request:{}",updateBlogRequest);
        return blogService.updateBlog(updateBlogRequest);
    }
    @RequestMapping("/delete")
    public boolean delete(@NotNull(message = "id不能为null") Integer blogId){
        log.info("删除博客,id:{}",blogId);
        return blogService.delete(blogId);
    }

    @RequestMapping("/CountBlog")
    public Integer CountBlog(){

        return blogService.CountBlog();
    }

}
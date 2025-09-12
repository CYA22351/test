package com.cya.springblogdemo.controller;

import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.response.BlogInfoResopnse;
import com.cya.springblogdemo.pojo.response.Result;
import com.cya.springblogdemo.service.BlogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
}
package com.cya.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cya.springblogdemo.mapper.BlogInfoMapper;
import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.response.BlogInfoResopnse;
import com.cya.springblogdemo.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 18:01
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired

    private BlogInfoMapper blogInfoMapper;

    @Override
    public List<BlogInfoResopnse> getList() {
        QueryWrapper<BlogInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag,0);
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(queryWrapper);

        List<BlogInfoResopnse> blogInfoResopnses = blogInfos.stream().map(x -> {
            BlogInfoResopnse resopnse = new BlogInfoResopnse();
            BeanUtils.copyProperties(x, resopnse);
            return resopnse;
        }).collect(Collectors.toList());
        return blogInfoResopnses;
    }
}
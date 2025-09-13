package com.cya.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cya.springblogdemo.mapper.BlogInfoMapper;
import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.response.BlogInfoResopnse;
import com.cya.springblogdemo.service.BlogService;
import com.cya.springblogdemo.util.BeanParseUtils;
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

        //将BlogInfo的数据化成BlogInfoResopnse格式
        List<BlogInfoResopnse> blogInfoResopnses = blogInfos.stream()
                .map(blogInfo -> BeanParseUtils.trans(blogInfo))
                .collect(Collectors.toList());
        return blogInfoResopnses;
    }

    @Override
    public BlogInfoResopnse getBlogDetail(Integer blogId) {
        //sql语句
        QueryWrapper<BlogInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag,0)
                        .eq(BlogInfo::getId,blogId);

        //如果返回多条数据会有异常
        BlogInfo blogInfo = blogInfoMapper.selectOne(queryWrapper);
//        换数据格式
        BlogInfoResopnse trans = BeanParseUtils.trans(blogInfo);
//        blogInfoMapper.selectById(blogId);
        return trans;
    }
}
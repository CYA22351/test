package com.cya.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cya.springblogdemo.common.exception.BlogException;
import com.cya.springblogdemo.constant.Constants;
import com.cya.springblogdemo.mapper.BlogInfoMapper;
import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.dataobject.UserInfo;
import com.cya.springblogdemo.pojo.request.AddBlogRequest;
import com.cya.springblogdemo.pojo.request.UpdateBlogRequest;
import com.cya.springblogdemo.pojo.response.BlogInfoResopnse;
import com.cya.springblogdemo.service.BlogService;
import com.cya.springblogdemo.util.BeanTransUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 18:01
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {


    @Autowired

    private BlogInfoMapper blogInfoMapper;

    @Override
    public List<BlogInfoResopnse> getList() {
        QueryWrapper<BlogInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag, Constants.BLOG_NORMAL);
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(queryWrapper);


        //将BlogInfo的数据化成BlogInfoResopnse格式
        List<BlogInfoResopnse> blogInfoResopnses = blogInfos.stream()
                .map(blogInfo -> BeanTransUtils.trans(blogInfo))
                .collect(Collectors.toList());
        return blogInfoResopnses;
    }

    @Override
    public BlogInfoResopnse getBlogDetail(Integer blogId) {

        return BeanTransUtils.trans(getBlogInfo(blogId));
    }

    @Override
    public BlogInfo getBlogInfo(Integer blogId){
        QueryWrapper<BlogInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag,Constants.BLOG_NORMAL)
                .eq(BlogInfo::getId,blogId);

        //如果返回多条数据会有异常
        BlogInfo blogInfo = blogInfoMapper.selectOne(queryWrapper);
        return blogInfo;
    }

    @Override
    public boolean addBlog(AddBlogRequest addBlogRequest) {
        BlogInfo blogInfo=new BlogInfo();
        BeanUtils.copyProperties(addBlogRequest,blogInfo);

        try {
            Integer insert = blogInfoMapper.insert(blogInfo);
            if (insert==1){
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("博客插入失败,e:",e);
            throw new BlogException("内部错误,请联系管理员");
        }
    }

    @Override
    public boolean updateBlog(UpdateBlogRequest updateBlogRequest) {

        try {
            Integer i = blogInfoMapper.updateById(BeanTransUtils.trans(updateBlogRequest));
            return i==1;
        }catch (Exception e){
            log.error("更新博客失败,e:",e);
            throw new BlogException("内部错误，请练习管理员");
        }

    }

    @Override
    public boolean delete(Integer blogId) {
        BlogInfo blogInfo=new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setDeleteFlag(Constants.BLOG_DELETE);
        try {
            Integer result = blogInfoMapper.updateById(blogInfo);
            return result==1;
        }catch (Exception e){
            log.error("删除博客失败,e:",e);
            throw new BlogException("内部错误，请练习管理员");
        }
    }

    @Override
    public Integer CountBlog() {
        QueryWrapper<BlogInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(BlogInfo::getDeleteFlag,Constants.BLOG_NORMAL);
        Integer integer = Math.toIntExact(blogInfoMapper.selectCount(queryWrapper));
        return integer;
    }
}
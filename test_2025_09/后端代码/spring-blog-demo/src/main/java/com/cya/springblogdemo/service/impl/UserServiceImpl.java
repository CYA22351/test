package com.cya.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cya.springblogdemo.common.exception.BlogException;
import com.cya.springblogdemo.mapper.UserInfoMapper;
import com.cya.springblogdemo.pojo.dataobject.BlogInfo;
import com.cya.springblogdemo.pojo.dataobject.UserInfo;
import com.cya.springblogdemo.pojo.request.UserLoginRequest;
import com.cya.springblogdemo.pojo.request.UserRegisterRequest;
import com.cya.springblogdemo.pojo.response.UserInfoResponse;
import com.cya.springblogdemo.pojo.response.UserLoginResponse;
import com.cya.springblogdemo.service.BlogService;
import com.cya.springblogdemo.service.UserService;
import com.cya.springblogdemo.util.BeanTransUtils;
import com.cya.springblogdemo.util.JwtUtils;
import com.cya.springblogdemo.util.SecurityUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/12 18:01
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Resource(name = "blogServiceImpl")
    private BlogService  blogService;

    @Override
    public UserLoginResponse checkPassword(UserLoginRequest userLoginRequest) {
        //查询数据库
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUserName,userLoginRequest.getUserName())
                .eq(UserInfo::getDeleteFlag,0);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo==null){
            throw new BlogException("用户不存在");
        }
//        if (!userLoginRequest.getPassword().equals(userInfo.getPassword())){
//            throw  new BlogException("用户密码错误");
//        }
        if (!SecurityUtil.verify(userLoginRequest.getPassword(),userInfo.getPassword()))
        {
            throw new BlogException("用户密码错误");
        }

        Map<String,Object> map=new HashMap<>();
        map.put("id",userInfo.getId());
        map.put("name",userInfo.getUserName());
        String token= JwtUtils.genToken(map);
        UserLoginResponse response=new UserLoginResponse(userInfo.getId(),token);
        return response;

    }

    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getDeleteFlag,0)
                .eq(UserInfo::getId,userId);
        UserInfo userInfo=userInfoMapper.selectOne(queryWrapper);

        return BeanTransUtils.trans(userInfo);
    }

    @Override
    public UserInfoResponse getAuthorInfo(Integer blogId) {
        BlogInfo blogInfo = blogService.getBlogInfo(blogId);

        if (blogInfo==null||blogInfo.getId()<=0){
            throw new BlogException("博客不存在");
        }
        return getUserInfo(blogInfo.getUserId());
    }

    @Override
    public boolean insertUserInfo(UserRegisterRequest userRegisterRequest) {

        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userRegisterRequest.getUserName());
        String sqlPassword=SecurityUtil.encrypt(userRegisterRequest.getPassword());
        userInfo.setPassword(sqlPassword);
        userInfo.setGithubUrl(userRegisterRequest.getGithubUrl());
try {
    Integer result = userInfoMapper.insert(userInfo);
    System.out.println(result);
    return result==1;
}catch (Exception e){
    log.info("注册用户失败：e:",e);
    throw new BlogException("注册用户失败");
}




    }
}
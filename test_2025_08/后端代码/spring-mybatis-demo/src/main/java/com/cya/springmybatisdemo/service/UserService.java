package com.cya.springmybatisdemo.service;

import com.cya.springmybatisdemo.mapper.UserInfoMapper;
import com.cya.springmybatisdemo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/21 11:26
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    public List<UserInfo> getAllUser() {
        return userInfoMapper.selectALL();
    }
}
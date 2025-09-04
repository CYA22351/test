package com.cya.bookmanagement.service;

import com.cya.bookmanagement.mapper.UserInfoMapper;
import com.cya.bookmanagement.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/4 9:07
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo queryUserInfoByNaem(String name) {
        return userInfoMapper.queryUserInfoByName(name);
    }
}
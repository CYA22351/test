package com.cya.bookmanagement.mapper;

import com.cya.bookmanagement.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/4 9:05
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where user_name=#{name}")
    UserInfo queryUserInfoByName(String name);
}
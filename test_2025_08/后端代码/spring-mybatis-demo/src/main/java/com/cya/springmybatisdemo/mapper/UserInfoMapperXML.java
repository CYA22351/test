package com.cya.springmybatisdemo.mapper;

import com.cya.springmybatisdemo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/30 9:43
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface UserInfoMapperXML {
    List<UserInfo> selectAll();

    List<UserInfo> selectAll2();

    Integer insertUser(UserInfo userInfo);

    Integer insertUser2(@Param("userinfo") UserInfo userInfo);

    Integer insertUser3( UserInfo userInfo);

    Integer updateUser(String password,Integer age,Integer id);

    Integer deleteUser(Integer id);

}
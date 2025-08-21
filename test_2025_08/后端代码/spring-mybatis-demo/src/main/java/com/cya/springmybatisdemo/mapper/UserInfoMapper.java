package com.cya.springmybatisdemo.mapper;

import com.cya.springmybatisdemo.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/20 17:43
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info")
    List<UserInfo> selectALL();

    @Select("select * from user_info where id=#{id}")
    UserInfo selectAllById(Integer id);

    @Select("select * from user_info where username=#{username} and password=#{password}")
    List<UserInfo> selectByNameAndPassword(String username,String password);
    //插入
    @Insert("insert into user_info (username,password,age) values (#{username},#{password},#{age})")
    Integer insertUser(UserInfo userInfo);
}
package com.cya.springtransdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/11 15:45
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface UserInfoMapper {
    @Insert("insert into user_info(`user_name`,`password`) values(#{name},#{password})")
    Integer insert(String name,String password);
}
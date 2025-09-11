package com.cya.springtransdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/11 15:44
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface LogInfoMapper {
    @Insert("insert into log_info(`user_name`,`op`)values(#{name},#{op})")
    Integer insertLog(String name,String op);
}
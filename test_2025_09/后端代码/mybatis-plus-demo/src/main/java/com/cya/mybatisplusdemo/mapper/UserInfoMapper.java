package com.cya.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cya.mybatisplusdemo.model.UserInfo;
import org.apache.coyote.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.tomcat.util.bcel.classfile.Constant;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/5 17:33
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Select(("select * from user_info where id=#{id}"))
    UserInfo selectById2(Integer id);

    UserInfo selectById3(Integer id);


    @Select("select id,username,password,age from user_info ${ew.customSqlSegment}")
    List<UserInfo> selectUserInfoByCondition(@Param("ew") Wrapper<UserInfo> queyWrapper);

    @Update("update user_info set age=age+#{addAge} ${ew.customSqlSegment}")
    Integer updateById2(Integer addAge,@Param("ew") Wrapper<UserInfo> ew);
}

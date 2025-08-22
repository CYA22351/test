    package com.cya.springmybatisdemo.mapper;

    import com.cya.springmybatisdemo.model.UserInfo;
    import org.apache.ibatis.annotations.*;

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

    //    @Results(id="BaseMap",value = {
    //            @Result(column = "delete_flag",property = "deleteFlag"),
    //            @Result(column = "create_time",property = "createTime"),
    //            @Result(column = "update_time",property = "updateTime")
    //
    //    })
        @Select("select * from user_info")
        List<UserInfo> selectALL();

        //@ResultMap(value = "BaseMap")
        @Select("select * from user_info where id=#{id}")
        UserInfo selectAllById(Integer id);

        @Select("select * from user_info where username=#{userName} and password=#{password}")
        List<UserInfo> selectByNameAndPassword(@Param("userName") String username,@Param("password") String password);
        //插入
        @Options(useGeneratedKeys = true,keyProperty = "id")//获取自增id
        @Insert("insert into user_info (username,password,age) values (#{username},#{password},#{age})")
        Integer insertUser(UserInfo userInfo);
    }
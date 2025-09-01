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
        //多个参数重定向
        @Select("select * from user_info where username=#{userName} and password=#{password}")
        List<UserInfo> selectByNameAndPassword(@Param("userName") String username,@Param("password") String password);

        @Select("select * from user_info where username='${userName}' and password='${password}'")
        List<UserInfo> selectByNameAndPassword2(@Param("userName") String username,@Param("password") String password);

        @Options(useGeneratedKeys = true,keyProperty = "id")
        @Insert("insert into user_info(username,password,age)"+
                "values (#{userinfo.username},#{userinfo.password},#{userinfo.age})")
        Integer insertUser2(@Param("userinfo") UserInfo userInfo);
        //插入
        @Options(useGeneratedKeys = true,keyProperty = "id")//获取自增id
        @Insert("insert into user_info (username,password,age) values (#{username},#{password},#{age})")
        Integer insertUser(UserInfo userInfo);

        //删除
        @Delete("delete from user_info where id=#{id}")
        Integer deleteUser(Integer id);

        //更新
        @Update("update user_info set delete_flag=#{deleteFlag},phone=#{phone} where id=#{id}")
        Integer updateUser(UserInfo userInfo);

        //数据排序必须用$,使用时必须防止sql注入，
        @Select("select * from user_info order by id ${order}")
        List<UserInfo> SelectUserInfoByOrder(String order);

        //模糊查询
        @Select("select * from user_info where username like CONCAT('%',#{username},'%')")
        List<UserInfo> SelectUserInfoByLike(String userNaem);
    }
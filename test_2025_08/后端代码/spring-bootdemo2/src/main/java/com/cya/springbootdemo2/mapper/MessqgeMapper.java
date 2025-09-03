package com.cya.springbootdemo2.mapper;

import com.cya.springbootdemo2.modle.MessageInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/3 9:11
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface MessqgeMapper {
//    查询所有信息
    @Select("select * from message_info where delete_flag=0")
    List<MessageInfo> queryAll();

//    from to在数据库可视化工具中 中是关键字，需要加反引号`
    @Insert("insert into message_info (`from`,`to`,message) values (#{from},#{to},#{message})")
    Integer insertMessqge(MessageInfo messageInfo);
}
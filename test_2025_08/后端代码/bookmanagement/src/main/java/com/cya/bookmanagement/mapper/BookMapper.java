package com.cya.bookmanagement.mapper;

import com.cya.bookmanagement.entity.BookInfo;
import com.cya.bookmanagement.entity.PageRequest;
import com.cya.bookmanagement.entity.ResponseResult;
import com.cya.bookmanagement.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/4 15:12
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface BookMapper {

    @Insert("insert into book_info (book_name,author,count,price,publish) " +
            "values (#{bookName},#{author},#{count},#{price},#{publish})")
    Integer addBook(BookInfo bookInfo);

    //每页显示图书信息，参数页码pageNum,pageSize每页个数
    @Select("select * from book_info")
    List<BookInfo> selectAllBook();

    @Select("select * from book_info where `status`!=0 limit #{offset},#{pageSize}")
    List<BookInfo> selectBooksByPage(PageRequest pageRequest);

    @Select("select count(1) from book_info where `status`!=0")
    Integer count();

    @Select("select * from book_info where `status`!=0 and  id=#{bookId}")
    BookInfo queryBookById(Integer bookId);

    Integer updateBook(BookInfo bookInfo);
}
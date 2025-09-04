package com.cya.bookmanagement.Controller;

import com.cya.bookmanagement.entity.BookInfo;
import com.cya.bookmanagement.entity.PageRequest;
import com.cya.bookmanagement.entity.ResponseResult;
import com.cya.bookmanagement.service.BookService;
import com.cya.bookmanagement.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/12 12:06
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j//日志
@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired//从spring容器中获取对象并赋值
    private BookService bookService;


    @RequestMapping("/addBook")
    public String addBook(BookInfo bookInfo){
        log.info("添加图书，request：{}",bookInfo);
        //{}占位符
        //1. 参数校验
        //2. 储存数据

        //3. 返回结果
        if (!StringUtils.hasLength(bookInfo.getBookName())||!StringUtils.hasLength(bookInfo.getAuthor())
        ||bookInfo.getCount()==null
        ||bookInfo.getPrice()==null
        ||!StringUtils.hasLength(bookInfo.getPublish())
        ||bookInfo.getStatus()==null){
            log.error("添加图书参数不合法,request:{}",bookInfo);

            return "参数不合法";
        }
        try {
            bookService.addBook(bookInfo);
            return "";
        }catch (Exception e){
            log.error("添加图书异常，e",e);
            return "添加图书发生异常";
        }

    }
    @RequestMapping("/getListByPage")
    public ResponseResult<BookInfo> getListByPage(PageRequest pageRequest){
        ResponseResult<BookInfo> listByPage = bookService.getListByPage(pageRequest);
        return listByPage;

    }

    @RequestMapping("/queryBookById")
    public BookInfo queryBookById(Integer bookId){
        log.info("查询图书信息，bookID:"+bookId);
       return bookService.queryBookById(bookId);
    }

    @RequestMapping("/updateBook")
    public String updateBook(BookInfo bookInfo){
        log.info("修改图书，bookInfo:{}",bookInfo);
        try {
            bookService.updateBook(bookInfo);
            //成功
            return "";
        }catch (Exception e){
            log.error("修改图书发生异常，e",e);
            return "修改图书发生异常";
        }
    }

}
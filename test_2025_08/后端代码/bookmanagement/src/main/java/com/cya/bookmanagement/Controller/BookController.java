package com.cya.bookmanagement.Controller;

import com.cya.bookmanagement.constant.Constants;
import com.cya.bookmanagement.entity.*;
import com.cya.bookmanagement.enums.BookStatusEnum;
import com.cya.bookmanagement.enums.ResultCodeEnum;
import com.cya.bookmanagement.service.BookService;
import com.cya.bookmanagement.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
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
    public Result<ResponseResult<BookInfo>> getListByPage(PageRequest pageRequest, HttpSession session){
        if (session.getAttribute(Constants.SESSION_USER_KEY)==null){
            return Result.unlogin();

        }
        UserInfo userInfo =(UserInfo) session.getAttribute(Constants.SESSION_USER_KEY);
        if (userInfo==null||userInfo.getId()<=0){
         return Result.unlogin();
        }

        ResponseResult<BookInfo> listByPage = bookService.getListByPage(pageRequest);
      return Result.success(listByPage);

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

    @RequestMapping("/deleteBook")
    public String delete(Integer BookId){
        log.info("删除，bookInfo:{}",BookId);
        try {
            BookInfo bookInfo=new BookInfo();
            bookInfo.setId(BookId);
            bookInfo.setStatus(BookStatusEnum.DELETED.getCode());
            bookService.updateBook(bookInfo);
            //成功
            return "";
        }catch (Exception e){
            log.error("修改图书发生异常，e",e);
            return "删除图书发生异常";
        }
    }

    @RequestMapping("/batchDeleteBook")
    public boolean batchDeleteBook(@RequestParam List<Integer> ids) {
        log.info("批量删除图书：ids{}",ids);
        try {
            bookService.batchDeleteBook(ids);

            return true;
        }catch (Exception e){
            log.error("批量删除图书失败,e:",e);
            return false;
        }
    }

}
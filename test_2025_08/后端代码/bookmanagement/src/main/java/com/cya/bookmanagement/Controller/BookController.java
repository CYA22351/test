package com.cya.bookmanagement.Controller;

import com.cya.bookmanagement.entity.BookInfo;
import com.cya.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired//从spring容器中获取对象并赋值
    private BookService bookService;
    @RequestMapping("getList")
public List<BookInfo> getList(){

        return bookService.getList();
    }

}
package com.cya.springbootdeom.controller;

import com.cya.springbootdeom.entiy.BookInfo;
import com.cya.springbootdeom.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/15 22:35
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("book")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    public List<BookInfo> getList(){
        return bookService.getList();
    }

}
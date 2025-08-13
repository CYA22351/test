package com.cya.bookmanagement.Controller;

import com.cya.bookmanagement.entity.BookInfo;
import com.cya.bookmanagement.service.BookService;
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
    @RequestMapping("getList")
public List<BookInfo> getList(){
        BookService bookService=new BookService();
        return bookService.getList();
    }

}
package com.cya.springbootdeom.service;

import com.cya.springbootdeom.dao.BookDao;
import com.cya.springbootdeom.entiy.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/15 23:05
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class BookService {
    @Autowired
    private BookDao bookDao;
    public List<BookInfo> getList(){
        List<BookInfo> kist=bookDao.mockData();
       for (BookInfo bookInfo:kist){
           if (bookInfo.getStatus()==1){
               bookInfo.setStatuscn("可借阅");
           }
           else {
               bookInfo.setStatuscn("不可借阅");
           }
       }
       return kist;
    }
}
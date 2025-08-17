package com.cya.bookmanagement.service;

import com.cya.bookmanagement.Dao.BookDao;
import com.cya.bookmanagement.entity.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/13 16:15
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    public List<BookInfo> getList(){

        List<BookInfo> list=bookDao.mockData();
        //对数据二次处理
        for (BookInfo bookInfo:list){
            if (bookInfo.getStatus()==1){
                bookInfo.setStatuscn("可借阅");
            }
            else {
                bookInfo.setStatuscn("不可借阅");
            }

        }
        return list;
    }
}
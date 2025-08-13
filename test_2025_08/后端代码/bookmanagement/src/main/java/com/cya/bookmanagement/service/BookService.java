package com.cya.bookmanagement.service;

import com.cya.bookmanagement.Dao.BookDao;
import com.cya.bookmanagement.entity.BookInfo;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/13 16:15
 * @description：
 * @modified By：
 * @version:
 */
public class BookService {
    public List<BookInfo> getList(){
        BookDao bookDao=new BookDao();
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
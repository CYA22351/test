package com.cya.bookmanagement.Dao;

import com.cya.bookmanagement.entity.BookInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/13 16:14
 * @description：
 * @modified By：
 * @version:
 */
@Repository//sring生成BookDao对象放入容器
public class BookDao {
    //假数据
    public List<BookInfo> mockData(){
        List<BookInfo> bookInfos=new ArrayList<>();
        for (int i=1;i<=15;i++){
            BookInfo bookInfo=new BookInfo();
            bookInfo.setBookId(i);
            bookInfo.setBookName("图书"+i);
            bookInfo.setAuthor("作者"+i);
            bookInfo.setPublish("出版社"+i);
            bookInfo.setNum(new Random().nextInt(100));
            bookInfo.setPrice(new BigDecimal(new Random().nextInt(100)));
            bookInfo.setStatus(i%5==0?2:1);//1可借阅，2不可借阅
            bookInfos.add(bookInfo);

        }
        return bookInfos;
    }
}
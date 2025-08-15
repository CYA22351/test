package com.cya.springbootdeom.dao;

import com.cya.springbootdeom.entiy.BookInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/15 22:54
 * @description：
 * @modified By：
 * @version:
 */
@Component//spring会生成对象放入容器中
public class BookDao {
    public List<BookInfo> bookInfos=new ArrayList<>();
   public List<BookInfo> mockData(){
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
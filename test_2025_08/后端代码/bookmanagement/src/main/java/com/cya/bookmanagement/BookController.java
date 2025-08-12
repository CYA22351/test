package com.cya.bookmanagement;

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
        List<BookInfo> list=mockData();
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
    //假数据
    public List<BookInfo> mockData(){
        List<BookInfo> bookInfos=new ArrayList<>();
        for (int i=0;i<15;i++){
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
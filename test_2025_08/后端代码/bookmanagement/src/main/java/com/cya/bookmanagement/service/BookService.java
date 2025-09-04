package com.cya.bookmanagement.service;


import com.cya.bookmanagement.entity.BookInfo;
import com.cya.bookmanagement.entity.PageRequest;
import com.cya.bookmanagement.entity.ResponseResult;
import com.cya.bookmanagement.enums.BookStatusEnum;
import com.cya.bookmanagement.mapper.BookMapper;
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
    private BookMapper bookMapper;

    

    public void addBook(BookInfo bookInfo) {
        bookMapper.addBook(bookInfo);
    }

    public ResponseResult<BookInfo> getListByPage(PageRequest pageRequest){
        Integer count=bookMapper.count();
        List<BookInfo> bookInfos = bookMapper.selectBooksByPage(pageRequest);


//对数据进行二次分析
        for (BookInfo bookInfo:bookInfos){
           bookInfo.setStatusCN(BookStatusEnum.getStatusByCode(bookInfo.getStatus()).getDesc());
        }

        return new ResponseResult<BookInfo>(count,bookInfos,pageRequest);
    }

    public BookInfo queryBookById(Integer bookId) {
        return bookMapper.queryBookById(bookId);
    }

    public void updateBook(BookInfo bookInfo) {
        bookMapper.updateBook(bookInfo);
    }
}
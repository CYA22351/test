package com.cya.springbootdemo2.service;

import com.cya.springbootdemo2.mapper.MessqgeMapper;
import com.cya.springbootdemo2.modle.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/3 9:19
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class MessqgeService {

    @Autowired
    private MessqgeMapper messqgeMapper;

    public void addMessage(MessageInfo messageInfo) {
        messqgeMapper.insertMessqge(messageInfo);
    }

    public List<MessageInfo> queryAll() {
        return messqgeMapper.queryAll();
    }
}
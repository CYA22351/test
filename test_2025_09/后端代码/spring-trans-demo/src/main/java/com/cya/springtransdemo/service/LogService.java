package com.cya.springtransdemo.service;

import com.cya.springtransdemo.mapper.LogInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/11 15:47
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@Service
public class LogService {

    @Autowired
    private LogInfoMapper logInfoMapper;
}
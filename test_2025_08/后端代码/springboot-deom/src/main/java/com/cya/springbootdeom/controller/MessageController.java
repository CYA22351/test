package com.cya.springbootdeom.controller;

import com.cya.springbootdeom.entiy.MessageInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/15 16:20
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("message")
@RestController
public class MessageController {
    private List<MessageInfo> messageInfos=new ArrayList<>();
    //ReRequestBody表示请求参数是json
    //produces = "application/jsom"返回是json
//    @RequestMapping(value = "publish")
//    public String publish(@RequestBody MessageInfo messageInfo){
//        if (!StringUtils.hasLength(messageInfo.getFrom())
//        ||!StringUtils.hasLength(messageInfo.getTo())
//        ||!StringUtils.hasLength(messageInfo.getMessage())){
//            return "{\"ok\":0}";
//        }
//        messageInfos.add(messageInfo);
//        return "{\"ok\":1}";
//    }
//    @RequestMapping("getList")
//    public List<MessageInfo> getList(){
//        return messageInfos;
//    }
    @RequestMapping(value = "publish")
    public String publish(@RequestBody MessageInfo messageInfo){
        if (!StringUtils.hasLength(messageInfo.getMessage())
        ||!StringUtils.hasLength(messageInfo.getFrom())
        ||!StringUtils.hasLength(messageInfo.getTo())){
            return "{\"ok\":0}";
        }
        messageInfos.add(messageInfo);
        return "{\"ok\":1}";
    }
    @RequestMapping("getList")
    public List<MessageInfo> getList(){
        return messageInfos;
    }

}
package com.cya.bookmanagement.config;

import com.cya.bookmanagement.entity.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/8 14:35
 * @description：
 * @modified By：
 * @version:
 */
//统一返回数据格式，只有String类型会进行处理
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //false：不处理，true：处理
        return true;
    }

    @SneakyThrows//
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

     if (body instanceof String){
         //转json
         return objectMapper.writeValueAsString(Result.success(body));
     }
     if (body instanceof Result){
         return body;
     }
        return Result.success(body);
    }
}
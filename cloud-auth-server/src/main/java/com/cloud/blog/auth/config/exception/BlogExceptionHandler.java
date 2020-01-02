package com.cloud.blog.auth.config.exception;

import com.cloud.blog.common.config.exception.BlogException;
import com.cloud.blog.common.config.exception.BlogResponse;
import com.cloud.blog.model.enums.EnumReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BlogExceptionHandler
 * @ription 全局异常处理类
 * @Author wsail
 * @Date 2019/12/25 15:03
 **/
@RestControllerAdvice
@Slf4j
public class BlogExceptionHandler {


    @ExceptionHandler
    @ResponseBody
    public BlogResponse<Object> handlerBlogException(HttpServletRequest request, BlogException exception){
        log.error("BlogException code:{},msg:{}",exception.getReturnCode().getCode(),exception.getReturnCode().getMsg());
        return new BlogResponse<Object>(exception.getReturnCode().getCode().toString(),exception.getReturnCode().getMsg());
    }


    @ExceptionHandler
    @ResponseBody
    public BlogResponse<Object> handlerException(HttpServletRequest request, Exception exception){
        log.error("exception error:{}",exception.getMessage());
        return new BlogResponse<Object>(EnumReturnCode.FAILURE.getCode().toString(),exception.getMessage());
    }



}

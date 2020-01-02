package com.cloud.blog.common.config.exception;

import com.cloud.blog.model.enums.EnumReturnCode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BlogObject
 * @Description 全局返回类型
 * @Author wsail
 * @Date 2019/12/25 12:16
 **/

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BlogResponse<T> implements Serializable {

    private String code;


    private String msg;


    private T data;


    public BlogResponse(){
        this.code = EnumReturnCode.SUCCESS.getCode().toString();
        this.msg = EnumReturnCode.SUCCESS.getMsg();
    }
    public BlogResponse(T data){
        this();
        this.data = data;
    }

    public BlogResponse(String code,String msg){
        this();
        this.code = code;
        this.msg = msg;
    }

    public BlogResponse(String code,String msg,T data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}

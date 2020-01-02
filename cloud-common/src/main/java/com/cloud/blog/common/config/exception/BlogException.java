package com.cloud.blog.common.config.exception;

import com.cloud.blog.model.enums.EnumReturnCode;

/**
 * @ClassName BlogException
 * @Description 统一异常捕获类
 * @Author wsail
 * @Date 2019/12/25 15:01
 **/

public class BlogException extends RuntimeException {

    private final EnumReturnCode returnCode;


    public BlogException(EnumReturnCode returnCode) {
        this.returnCode = returnCode;
    }


    public EnumReturnCode getReturnCode(){
        return returnCode;
    }
}

package com.cloud.blog.user.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BlogCallBackController
 * @Description 接受认证成功的回调地址
 * @Author wsail
 * @Date 2019/12/27 15:16
 **/
@RestController
public class BlogCallBackController {

    @GetMapping(value = "/callback",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String authorizationSuccessCallBack(String code){
        String a = "hahah";
        return a+code;
    }

}

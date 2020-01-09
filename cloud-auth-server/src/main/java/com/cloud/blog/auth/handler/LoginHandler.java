package com.cloud.blog.auth.handler;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName LoginHandler
 * @Description 登录处理器
 * @Author wsail
 * @Date 2020/1/6 16:57
 **/
@RestController
@RequestMapping("/security")
public class LoginHandler {


    @GetMapping(value = "/login/page",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView loginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/html/login.html");
        return modelAndView;
    }
}

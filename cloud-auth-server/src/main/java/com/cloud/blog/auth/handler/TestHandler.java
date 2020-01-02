package com.cloud.blog.auth.handler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TestHandler
 * @Description test
 * @Author wsail
 * @Date 2019/12/29 16:54
 **/
@RestController
@RequestMapping("/oauth")
public class TestHandler {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    public static void main(String[] args) throws ParseException {

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");


        Date date = df.parse("2019-12-31");
        System.out.println(date);
        System.out.println(df.format(date));
    }
}

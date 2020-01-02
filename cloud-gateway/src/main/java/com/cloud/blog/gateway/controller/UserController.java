package com.cloud.blog.gateway.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author wsail
 * @Date 2019/12/17 15:20
 **/
@RestController
public class UserController {

    @GetMapping(value = "/get" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object get(Authentication authentication){

        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        return details.getTokenValue();
    }




}

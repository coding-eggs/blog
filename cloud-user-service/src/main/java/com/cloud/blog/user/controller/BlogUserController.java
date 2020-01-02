package com.cloud.blog.user.controller;

import com.cloud.blog.common.config.exception.BlogResponse;
import com.cloud.blog.model.enums.EnumReturnCode;
import com.cloud.blog.user.config.pojo.BaseSecurityUser;
import com.cloud.blog.user.service.IBlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName BlogUserController
 * @Description
 * @Author wsail
 * @Date 2019/12/25 15:46
 **/

@RestController
@RequestMapping("/sys/user")
public class BlogUserController {

    @Autowired
    private IBlogUserService blogUserService;


    @PostMapping(value = "/register",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BlogResponse<Object> createBlogUser(@RequestBody BaseSecurityUser user){
        if(blogUserService.addBlogUser(user)){
            return new BlogResponse<>();
        }
        return new BlogResponse<Object>(EnumReturnCode.FAILURE.getCode().toString(),EnumReturnCode.FAILURE.getMsg());
    }

    @GetMapping(value = "/get")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Object get(Authentication authentication){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        String token = details.getTokenValue();
        return token;
    }

}

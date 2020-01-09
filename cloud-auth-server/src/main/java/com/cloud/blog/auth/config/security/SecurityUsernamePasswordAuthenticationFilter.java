package com.cloud.blog.auth.config.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SecurityUsernamePasswordAuthenticationFilter
 * @Description TODO
 * @Author wsail
 * @Date 2020/1/6 19:14
 **/
public class SecurityUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public SecurityUsernamePasswordAuthenticationFilter() {
        super();
        super.setPostOnly(false);
        super.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
    }


    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return super.obtainUsername(request);
    }
}

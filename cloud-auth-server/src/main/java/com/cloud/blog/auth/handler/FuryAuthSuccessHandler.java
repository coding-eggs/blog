package com.cloud.blog.auth.handler;

import com.cloud.blog.auth.config.pojo.BaseSecurityUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: blog
 * @description: 登录成功处理器
 * @author: Ailuoli
 * @create: 2019-05-14 16:31
 **/
@Component
@Slf4j
public class FuryAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();

        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");

        String requestURI = "/index";
        try {
            requestURI = defaultSavedRequest.getRedirectUrl();
        }catch (Exception e) {
            log.info(e.getMessage());
        }

        //获取用户信息
        BaseSecurityUser user = (BaseSecurityUser) authentication.getPrincipal();

//        //更新最后一次登录时间
//        user.setLastLoginTime(new Date());
//        try {
//            blogUserMapper.updateUserLoginTime(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","登录成功");
        map.put("status",true);
        map.put("data",user);
        map.put("requestURI",requestURI);
        response.setContentType("application/json;charset=UTF-8");

        //获取请求类型
        String type = request.getHeader("X-Requested-With")==null?"":request.getHeader("X-Requested-With");

        if("XMLHttpRequest".equals(type)) {
            //设置响应头为重定向
            response.getWriter().write(objectMapper.writeValueAsString(map));
            return;
        }

        response.sendRedirect(request.getContextPath()+requestURI);
    }
}


package com.cloud.blog.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: blog
 * @description: 权限不足处理器
 * @author: Ailuoli
 * @create: 2019-05-14 16:49
 **/
@Component
public class RestAuthAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        Map<String,Object> map=new HashMap<>(2);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        map.put("code", "403");
        map.put("data",null);
        map.put("status",false);
        map.put("msg", accessDeniedException.getMessage());
        response.sendRedirect("/403");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}


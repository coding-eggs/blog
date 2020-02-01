package com.cloud.blog.auth.config.security;

import com.cloud.blog.model.enums.EnumReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SecurityTokenInfo
 * @Description token_info
 * @Author wsail
 * @Date 2020/1/15 17:41
 **/

@RestController
public class SecurityTokenInfo {


    @Autowired
    private TokenStore jwtTokenStore;

    @RequestMapping("/blog/user_info")
    public Map<String,Object> user(@RequestHeader String authorization) {
        Map<String,Object> map = new HashMap<>();
        OAuth2Authentication auth2Authentication = jwtTokenStore.readAuthentication(authorization.split(" ")[1]);
        if(auth2Authentication == null){
            map.put(EnumReturnCode.INVALID_ACCESS_TOKEN.getCode().toString(),EnumReturnCode.INVALID_ACCESS_TOKEN.getMsg());
        }
        map.put("user",auth2Authentication.getPrincipal());
        map.put("authorities",auth2Authentication.getAuthorities());
        return map;
    }

}

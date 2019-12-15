package com.cloud.blog.auth.config;

import com.cloud.blog.auth.config.pojo.BaseSecurityUser;
import com.cloud.blog.auth.config.pojo.Constant;
import com.cloud.blog.model.po.sys.BlogUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * @program: blog
 * @description: jwt token 转换器
 * @author: Ailuoli
 * @create: 2019-12-16 00:39
 **/
public class JwtAccessToken extends JwtAccessTokenConverter {


    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {

        OAuth2AccessToken oAuth2AccessToken = super.extractAccessToken(value,map);

        convertData(oAuth2AccessToken,oAuth2AccessToken.getAdditionalInformation());

        return oAuth2AccessToken;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);

        // 设置额外用户信息
        BlogUser blogUser =((BaseSecurityUser)authentication.getPrincipal()).getBlogUser();

        blogUser.setCredential(null);
        //将用户信息添加到token额外信息中
        defaultOAuth2AccessToken.getAdditionalInformation().put(Constant.USER_INFO,blogUser);

        return super.enhance(defaultOAuth2AccessToken,authentication);

    }

    private void convertData(OAuth2AccessToken accessToken,Map<String,?> map){
        accessToken.getAdditionalInformation().put(Constant.USER_INFO,convertUserData(map.get(Constant.USER_INFO)));
    }

    private BlogUser convertUserData(Object map){
        String json = JsonUtils.deserializer(map);
        return JsonUtils.serializable(json,BlogUser.class);
    }

}


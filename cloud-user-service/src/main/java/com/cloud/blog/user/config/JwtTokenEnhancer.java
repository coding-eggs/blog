package com.cloud.blog.user.config;

import com.cloud.blog.model.po.sys.BlogUser;
import com.cloud.blog.user.config.pojo.BaseSecurityUser;
import com.cloud.blog.model.po.sys.Constant;
import com.cloud.blog.user.util.JsonUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName JwtTokenEnhancer
 * @Description jwt 增强类，往 jwt里写入用户信息
 * @Author wsail
 * @Date 2019/12/17 15:57
 **/
@Component
public class JwtTokenEnhancer extends JwtAccessTokenConverter {

    /**
     * @Author myk
     * @Description //将用户信息写入token
     * @Date 16:24 2019/12/17
     * @Param [oAuth2AccessToken, oAuth2Authentication]
     * @return org.springframework.security.oauth2.common.OAuth2AccessToken
     **/
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(oAuth2AccessToken);
        BaseSecurityUser blogUser = (BaseSecurityUser) oAuth2Authentication.getPrincipal();
        blogUser.setCredential(null);
        defaultOAuth2AccessToken.getAdditionalInformation().put(Constant.USER_INFO,blogUser);
        return super.enhance(defaultOAuth2AccessToken,oAuth2Authentication);
    }

    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken,  Map<String, ?> map) {
        accessToken.getAdditionalInformation().put(Constant.USER_INFO,convertUserData(map.get(Constant.USER_INFO)));

    }

    private BlogUser convertUserData(Object map) {
        String json = JsonUtils.deserializer(map);
        return JsonUtils.serializable(json, BlogUser.class);
    }


}

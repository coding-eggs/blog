package com.cloud.blog.auth.config.security;

import com.cloud.blog.auth.config.pojo.BaseSecurityUser;
import com.cloud.blog.auth.config.pojo.QQUserInfo;
import com.cloud.blog.auth.mapper.sys.BlogUserMapper;
import com.cloud.blog.model.enums.EnumIdentifyType;
import com.cloud.blog.model.enums.EnumSex;
import com.cloud.blog.model.po.sys.BlogRole;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: blog
 * @description: 管理权限类
 * @author: Ailuoli
 * @create: 2019-05-27 14:51
 **/
@Component
public class QQAuthenticationManager implements AuthenticationManager {


    private  BlogUserMapper blogUserMapper;


    private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();


    /**
     * 获取用户信息
     */
    private final static String USER_INFO_URL = "https://graph.qq.com/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s";

    /**
     * client_id 由腾讯提供(即AppId)
     */
    private static final String CLIENT_ID = "101583722";

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("USER"));
    }

    public QQAuthenticationManager(BlogUserMapper blogUserMapper) {
        this.blogUserMapper = blogUserMapper;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        QQUserInfo qqUserInfo = null;
        if(authentication.getName() != null && authentication.getCredentials()!=null)
        {
            qqUserInfo = getUserInfo(authentication.getName(), (String) authentication.getCredentials());
        }
        BaseSecurityUser securityBlogUser = new BaseSecurityUser();
        if(qqUserInfo != null) {

            List<BlogRole> list = new ArrayList<>();
            BlogRole blogRole = new BlogRole();
            blogRole.setRoleName("USER");

            list.add(blogRole);
            securityBlogUser.setFigureUrl("https"+qqUserInfo.getFigureurl_qq().substring(4));
            securityBlogUser.setNickname(qqUserInfo.getNickname());
            securityBlogUser.setSex("男".equals(qqUserInfo.getGender())? EnumSex.ENUM_MAN.getValue():EnumSex.ENUM_WOMAN.getValue());
            securityBlogUser.setLastLoginTime(new Date());
            securityBlogUser.setNickname(qqUserInfo.getNickname());
            securityBlogUser.setAuthorities(list);
            securityBlogUser.setCredential((String) authentication.getCredentials());

            try {
                if(blogUserMapper.selectQQLoginInfo(securityBlogUser.getCredential(), EnumIdentifyType.LOGIN_IDENTIFY_QQ.getValue()) == 0){
                    securityBlogUser.setLastPasswordResetTime(new Date());
                    blogUserMapper.insertSelective(securityBlogUser);
                }else {
                    blogUserMapper.updateByPrimaryKey(securityBlogUser);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return new UsernamePasswordAuthenticationToken(securityBlogUser,null,AUTHORITIES);
    }

    /**
     * 获取QQ授权后的基本信息
     * @param accessToken
     * @param openId
     * @return
     */
    private QQUserInfo getUserInfo(String accessToken, String openId) {
        String url = String.format(USER_INFO_URL,accessToken,CLIENT_ID,openId);
        RestTemplate template = new RestTemplate();
        String userInfoResult = template.getForObject(url, String.class);
        return jsonToObject(userInfoResult, QQUserInfo.class);
    }

    private <T> T jsonToObject(String json,Class<T> targetClass){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(json,targetClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


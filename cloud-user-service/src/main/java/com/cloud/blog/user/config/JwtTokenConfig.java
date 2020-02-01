package com.cloud.blog.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @ClassName JwtTokenConfig
 * @Description jwt config
 * @Author wsail
 * @Date 2019/12/17 11:25
 **/
@Configuration
public class JwtTokenConfig {
    @Bean
    @Primary
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * @Author myk
     * @Description 转换器
     * @Date 16:19 2019/12/17
     * @Param []
     * @return org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     **/
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        //用作 JWT 转换器
        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("public.cert");
        String publicKey ;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(publicKey); //设置公钥
        return converter;
    }


}

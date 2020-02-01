package com.cloud.blog.auth.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

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
        final JwtAccessTokenConverter converter = new JwtTokenEnhancer();
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("ailuoli.jks"), "Ws961226".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("ailuoli"));
        return converter;
    }
}

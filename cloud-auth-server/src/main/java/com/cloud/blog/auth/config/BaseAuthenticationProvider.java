package com.cloud.blog.auth.config;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @program: cloudblog
 * @description:
 * @author: Ailuoli
 * @create: 2019-12-08 22:32
 **/
@Component
public class BaseAuthenticationProvider extends DaoAuthenticationProvider {



    protected MessageSourceAccessor message = SpringSecurityMessageSource.getAccessor();

    @Inject
    public BaseAuthenticationProvider(@Named("baseUserDetailService") UserDetailsService userDetailsService) {
        this.setUserDetailsService(userDetailsService);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}


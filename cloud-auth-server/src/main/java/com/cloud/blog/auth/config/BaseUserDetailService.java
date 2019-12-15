package com.cloud.blog.auth.config;

import com.cloud.blog.auth.mapper.sys.BlogUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program: cloudblog
 * @description:
 * @author: Ailuoli
 * @create: 2019-12-08 22:53
 **/
@Service
public class BaseUserDetailService implements UserDetailsService {


    @Autowired
    private BlogUserMapper blogUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}


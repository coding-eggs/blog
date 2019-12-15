package com.cloud.blog.auth.config;

import com.cloud.blog.auth.config.pojo.BaseSecurityUser;
import com.cloud.blog.auth.mapper.sys.BlogRoleMapper;
import com.cloud.blog.auth.mapper.sys.BlogUserMapper;
import com.cloud.blog.model.po.sys.BlogRole;
import com.cloud.blog.model.po.sys.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private BlogRoleMapper blogRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名获取用户信息
        BlogUser user = blogUserMapper.selectUserByUsername(username);
        //获取用户权限
        List<BlogRole> authorityList;
        User securityUser = null;
        if(Optional.ofNullable(user).isPresent()){
            authorityList = blogRoleMapper.selectRoleListByUserId(user.getId());
            securityUser = new User(user.getUsername(),user.getCredential(),isActive(user.getIsLock()),true,true,true,authorityList);
        }

        return new BaseSecurityUser(securityUser,user);
    }

    private boolean isActive(int active){
        return active == 1;
    }
}


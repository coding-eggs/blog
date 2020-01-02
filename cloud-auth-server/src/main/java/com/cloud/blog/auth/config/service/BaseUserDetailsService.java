package com.cloud.blog.auth.config.service;

import com.cloud.blog.auth.config.pojo.BaseSecurityUser;
import com.cloud.blog.auth.mapper.sys.BlogRoleMapper;
import com.cloud.blog.auth.mapper.sys.BlogUserMapper;
import com.cloud.blog.model.po.sys.BlogRole;
import com.cloud.blog.model.po.sys.BlogUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BaseUserDetailsService
 * @Description userDetailsService
 * @Author Ailuoli
 * @Date 2019/12/17 10:59
 **/

@Slf4j
@Service
public class BaseUserDetailsService implements UserDetailsService {

    @Autowired
    private BlogUserMapper blogUserMapper;

    @Autowired
    private BlogRoleMapper blogRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("username:"+ s);
        BaseSecurityUser user = blogUserMapper.selectUserByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("can not find this user  ,username: "+ s);
        }
        //根据用户id 获取角色列表
        List<BlogRole> roleList = blogRoleMapper.selectRoleListByUserId(user.getId());
        user.setAuthorities(roleList);
        return user;
    }
}

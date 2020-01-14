package com.cloud.blog.user.service.impl;

import com.cloud.blog.common.config.exception.BlogException;
import com.cloud.blog.model.enums.EnumIdentifyType;
import com.cloud.blog.model.enums.EnumReturnCode;
import com.cloud.blog.model.po.sys.dto.BlogUserLoginDTO;
import com.cloud.blog.user.config.pojo.BaseSecurityUser;
import com.cloud.blog.user.mapper.sys.BlogUserMapper;
import com.cloud.blog.user.service.IBlogUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName BlogUserServiceImp
 * @Description 用户service实现类
 * @Author wsail
 * @Date 2019/12/24 16:50
 **/
@Service
public class BlogUserServiceImpl implements IBlogUserService {

    @Autowired
    private BlogUserMapper blogUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean addBlogUser(BaseSecurityUser user) {

        //如果是本系统注册
        if(EnumIdentifyType.LOGIN_IDENTIFY_LOCAL.equals(user.getIdentifyType())){
            //username 不能为空
            if(StringUtils.isEmpty(user.getUsername())){
                throw new BlogException(EnumReturnCode.USERNAME_IS_EMPTY);
            }
            //同一种认证类型得用户名不能重复
            if(blogUserMapper.selectCountUsername(user.getUsername(),user.getIdentifyType()) > 0){
                throw new BlogException(EnumReturnCode.USERNAME_IS_EXIST);
            }
            //插入前对密码进行加密
            user.setCredential(passwordEncoder.encode(user.getPassword()));
        }
        return blogUserMapper.insertBlogUser(user) > 0;
    }

    @Override
    public BlogUserLoginDTO login(String username, String password) {
        return null;
    }
}

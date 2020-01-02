package com.cloud.blog.user.service;

import com.cloud.blog.model.po.sys.dto.BlogUserLoginDTO;
import com.cloud.blog.user.config.pojo.BaseSecurityUser;

/**
 * @ClassName IBlogUserService
 * @Description 用户service
 * @Author wsail
 * @Date 2019/12/24 16:46
 **/
public interface IBlogUserService {

    boolean addBlogUser(BaseSecurityUser user);

    BlogUserLoginDTO login(String username,String password);

}

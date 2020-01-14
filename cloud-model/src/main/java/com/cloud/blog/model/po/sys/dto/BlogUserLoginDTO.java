package com.cloud.blog.model.po.sys.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

/**
 * @ClassName BlogUserLoginDTO
 * @Description 登录dto
 * @Author wsail
 * @Date 2019/12/25 16:22
 **/
@Data
public class BlogUserLoginDTO {


    private User user;

}

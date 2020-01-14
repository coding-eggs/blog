package com.cloud.blog.model.co.oauth;

import lombok.Data;

/**
 * @ClassName JwtCo
 * @Description 请求jwt 入参
 * @Author wsail
 * @Date 2020/1/11 19:47
 **/
@Data
public class JwtCo {

    private String grant_type;

    private String client_id;

    private String client_secret;

    private String redirect_uri;

    private String code;

    private String password;

    private String scope;
}

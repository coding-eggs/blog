package com.cloud.blog.model.po.sys.jwt;

import lombok.Data;

/**
 * @ClassName JWT
 * @Description jwt 实体类
 * @Author wsail
 * @Date 2019/12/25 16:20
 **/

@Data
public class JWT {

    private String access_token;

    private String token_type;

    private String refresh_token;

    private int expires_in;

    private String scope;

    private String jti;


}

package com.cloud.blog.auth.config.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 2018-12-18 11:47
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class QQAccessToken {

    /**
     * 授权accessToken
     */
    private String accessToken;

    /**
     * 该access token的有效期，单位为秒。
     */
    private Integer expiresIn;

    /**
     * 在授权自动续期步骤中，获取新的Access_Token时需要提供的参数。
     */
    private String refreshToken;

}

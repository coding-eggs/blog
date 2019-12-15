package com.cloud.blog.model.po.sys.oauth2;

import lombok.Data;

@Data
public class OauthRefreshToken {

    private String tokenId;

    private byte[] token;

    private byte[] authentication;

}
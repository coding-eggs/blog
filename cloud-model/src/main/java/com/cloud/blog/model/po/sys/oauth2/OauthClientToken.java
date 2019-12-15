package com.cloud.blog.model.po.sys.oauth2;

import lombok.Data;

@Data
public class OauthClientToken {

    private String authenticationId;

    private String tokenId;

    private String userName;

    private String clientId;

    private byte[] token;

}
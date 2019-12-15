package com.cloud.blog.model.po.sys.oauth2;

import lombok.Data;

@Data
public class ClientDetails {
    private String appid;

    private String resourceids;

    private String appsecret;

    private String scope;

    private String granttypes;

    private String redirecturl;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalinformation;

    private String autoapprovescopes;


}
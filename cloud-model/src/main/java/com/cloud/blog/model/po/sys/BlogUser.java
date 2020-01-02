package com.cloud.blog.model.po.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogUser {
    private Long id;

    private String username;

    private Integer identifyType = 10;

    private String credential;

    private String nickname;

    private Date birthday;

    private String figureUrl;

    private Byte sex;

    private String phone;

    private String mail;

    private Date lastLoginTime;

    private Date createTime;

    private Date modifyTime;

    private Date lastPasswordResetTime;

    private Byte isLock;

    private Byte isDelete;


}

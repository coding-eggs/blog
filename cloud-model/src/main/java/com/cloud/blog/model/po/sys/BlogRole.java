package com.cloud.blog.model.po.sys;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class BlogRole implements GrantedAuthority {

    private Long id;

    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
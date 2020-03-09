package com.cloud.blog.auth.config.pojo;

import com.cloud.blog.model.po.sys.BlogRole;
import com.cloud.blog.model.po.sys.BlogUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName BaseSecurityUser
 * @Description security user
 * @Author wsail
 * @Date 2019/12/17 11:07
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "com.cloud.blog.auth.config.pojo.BaseSecurityUser",description = "")
public class BaseSecurityUser extends BlogUser implements UserDetails, Serializable {

    @ApiModelProperty(value = "角色列表")
    private List<BlogRole> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getCredential();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

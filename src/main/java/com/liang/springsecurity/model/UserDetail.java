package com.liang.springsecurity.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.liang.springsecurity.constant.UserStatus;
import com.liang.springsecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Optional;

/**
 * UserDetail 实现类
 *
 * @author Liang
 * 2022-09-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDetail extends User implements UserDetails {

    /**
     * 权限列表
     */
    @JSONField(deserialize = false)
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetail(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setStatus(user.getStatus());
        this.setCreatedAt(user.getCreatedAt());
        this.setUpdatedAt(user.getUpdatedAt());
    }

    public static UserDetail currentUser() {
        return Optional.ofNullable(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .filter(it -> it instanceof UserDetail)
                .map(it -> (UserDetail) it)
                .orElseThrow(() -> new RuntimeException("用户未登陆"));
    }

    /**
     * 添加用户拥有的权限和角色
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * 账户是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     */
    @Override
    public boolean isEnabled() {
        return ObjectUtils.nullSafeEquals(UserStatus.ENABLE.getCode(), this.getStatus());
    }
}

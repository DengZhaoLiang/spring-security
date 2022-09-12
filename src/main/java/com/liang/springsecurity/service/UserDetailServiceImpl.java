package com.liang.springsecurity.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.springsecurity.entity.Permission;
import com.liang.springsecurity.entity.Role;
import com.liang.springsecurity.entity.RolePermission;
import com.liang.springsecurity.entity.User;
import com.liang.springsecurity.entity.UserRole;
import com.liang.springsecurity.mapper.PermissionMapper;
import com.liang.springsecurity.mapper.RoleMapper;
import com.liang.springsecurity.mapper.RolePermissionMapper;
import com.liang.springsecurity.mapper.UserRoleMapper;
import com.liang.springsecurity.model.UserDetail;
import com.liang.springsecurity.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetailsService实现类
 *
 * @author Liang
 * 2022-09-10
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户信息
        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        UserDetail userDetail = new UserDetail(user);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // 用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        // 获取用户角色
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", user.getId());
        List<Long> roleIds = userRoleMapper.selectList(userRoleQueryWrapper)
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());

        QueryWrapper<Role> roleWrapper = new QueryWrapper<>();
        roleWrapper.in("id", roleIds);
        List<String> roles = roleMapper.selectList(roleWrapper)
                .stream()
                .map(role -> {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                    return role.getName();
                })
                .collect(Collectors.toList());
        userDetail.setRoles(roles);

        // 获取用户权限
        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
        rolePermissionQueryWrapper.in("role_id", roleIds);
        List<Long> permissionIds = rolePermissionMapper.selectList(rolePermissionQueryWrapper)
                .stream()
                .map(RolePermission::getPermissionId)
                .distinct()
                .collect(Collectors.toList());

        QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.in("id", permissionIds);
        permissionMapper.selectList(permissionQueryWrapper)
                .forEach(permission ->
                        authorities.add(new SimpleGrantedAuthority(permission.getName())));
        userDetail.setAuthorities(authorities);
        log.info("authorities:{}", JSON.toJSONString(authorities));

        //这里返回的是我们自己定义的UserDetail
        return userDetail;
    }
}

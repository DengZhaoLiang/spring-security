package com.liang.springsecurity.entity;

import lombok.Data;

/**
 * 角色权限实体类
 *
 * @author Liang
 * 2022-09-12
 */
@Data
public class RolePermission {

    /**
     * 角色权限ID
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 修改时间
     */
    private Long updatedAt;
}

package com.liang.springsecurity.entity;

import lombok.Data;

/**
 * 用户角色实体类
 *
 * @author Liang
 * 2022-09-12
 */
@Data
public class UserRole {

    /**
     * 用户角色ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 修改时间
     */
    private Long updatedAt;
}

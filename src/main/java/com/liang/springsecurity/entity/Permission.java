package com.liang.springsecurity.entity;

import lombok.Data;

/**
 * 权限实体类
 *
 * @author Liang
 * 2022-09-12
 */
@Data
public class Permission {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 修改时间
     */
    private Long updatedAt;
}

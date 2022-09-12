package com.liang.springsecurity.entity;

import lombok.Data;

/**
 * 角色实体类
 *
 * @author Liang
 * 2022-09-12
 */
@Data
public class Role {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名
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

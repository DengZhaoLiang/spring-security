package com.liang.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * 用户实体类
 *
 * @author Liang
 * 2022-09-12
 */
@Data
public class User {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 昵称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    @TableField(exist = false)
    private List<String> roles;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 修改时间
     */
    private Long updatedAt;
}

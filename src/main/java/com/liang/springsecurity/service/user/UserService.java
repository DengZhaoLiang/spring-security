package com.liang.springsecurity.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.springsecurity.entity.User;

/**
 * UserService
 *
 * @author Liang
 * 2022-09-12
 */
public interface UserService extends IService<User> {

    /**
     * 通过姓名查找用户
     */
    User getUserByUsername(String username);
}

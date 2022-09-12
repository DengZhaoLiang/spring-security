package com.liang.springsecurity.service;

import com.liang.springsecurity.model.UserDetail;

/**
 * @author Liang
 * 2022-09-12
 */
public interface LoginService {

    /**
     * 账号密码登录
     */
    String login(UserDetail userDetail);

    /**
     * 第三方登录
     */
    String thirdPartyLogin(String certificate);
}

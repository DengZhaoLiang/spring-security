package com.liang.springsecurity.controller;

import com.liang.springsecurity.model.UserDetail;
import com.liang.springsecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author Liang
 * 2022-09-12
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody UserDetail userDetail) {
        return loginService.login(userDetail);
    }

    @PostMapping("/thirdPartyLogin")
    public String thirdPartyLogin(@RequestParam String certificate) {
        return loginService.thirdPartyLogin(certificate);
    }
}

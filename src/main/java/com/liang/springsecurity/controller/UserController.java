package com.liang.springsecurity.controller;

import com.liang.springsecurity.model.UserDetail;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 *
 * @author Liang
 * 2022-09-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/role")
    public List<String> role() {
        return UserDetail.currentUser().getRoles();
    }

    @PreAuthorize("hasAuthority('all')")
    @GetMapping("/profile")
    public UserDetail authority() {
        return UserDetail.currentUser();
    }

}

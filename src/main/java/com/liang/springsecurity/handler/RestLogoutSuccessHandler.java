package com.liang.springsecurity.handler;

import com.liang.springsecurity.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出成功的调用类
 *
 * @author Liang
 */
@Component
public class RestLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) {
        Result.writeResponse(response, HttpStatus.OK, "退出登陆");
    }
}
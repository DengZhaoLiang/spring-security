package com.liang.springsecurity.handler;

import com.liang.springsecurity.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 匿名未登录的时候访问,需要登录的资源的调用类
 *
 * @author Liang
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authenticationException) {
        Result.writeResponse(response, HttpStatus.UNAUTHORIZED, authenticationException.getMessage());
    }
}
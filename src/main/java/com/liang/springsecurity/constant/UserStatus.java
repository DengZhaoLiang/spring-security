package com.liang.springsecurity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户状态枚举类
 *
 * @author Liang
 * 2022-09-10
 */
@RequiredArgsConstructor
public enum UserStatus {

    /**
     * 启用
     */
    ENABLE(1),

    /**
     * 禁用
     */
    DISABLE(0),

    ;

    @Getter
    private final int code;
}

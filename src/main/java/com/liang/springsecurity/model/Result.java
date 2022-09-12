package com.liang.springsecurity.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 统一返回结果封装类
 *
 * @author Liang
 * 2022-09-10
 */
@Data
public class Result<T> {

    /**
     * 成功的code码
     */
    private Integer status;

    /**
     * 请求失败返回的提示信息，给前端进行页面展示的信息
     */
    private String message;

    /**
     * 成功时返回的数据，失败时返回具体的异常信息
     */
    private T data;

    public Result() {
    }

    public Result(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public Result(HttpStatus status, String message, T data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    @SneakyThrows
    public static void writeResponse(HttpServletResponse response, HttpStatus status, String message) {
        // 设置response状态码，返回错误信息等
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(status.value());
        try (PrintWriter printWriter = response.getWriter()) {
            Result<String> result = new Result<>(status, message);
            printWriter.write(JSON.toJSONString(result));
            printWriter.flush();
        }
    }
}

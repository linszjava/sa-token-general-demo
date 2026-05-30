package com.size.springboot3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TODO
 *
 * @author linsz
 * @version v1.0
 * @date 2026/5/31 01:32
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未登录或登录已过期"),
    FORBIDDEN(403, "无权限访问"),
    USER_EXISTS(1001, "用户名已存在"),
    USER_NOT_EXISTS(1002, "无该用户")

    ;

    private final Integer code;
    private final String msg;
}

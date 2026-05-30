package com.size.springboot3.exception;

import lombok.Getter;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/5/31 01:25
 */
@Getter
public class CommonException extends RuntimeException {

    private final Integer code;


    public CommonException(String message) {
        this(ResultCode.FAIL.getCode(), message);
    }
    public CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public CommonException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }
    public CommonException(ResultCode resultCode, String message) {
        this(resultCode.getCode(), message);
    }
    public CommonException(String message, Throwable cause) {
        this(ResultCode.FAIL.getCode(), message, cause);
    }
    public CommonException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }



}

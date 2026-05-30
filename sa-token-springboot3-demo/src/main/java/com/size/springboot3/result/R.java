package com.size.springboot3.result;

import com.size.springboot3.exception.ResultCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一 API 响应体
 *
 * @author linsz
 */
@Getter
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Integer code;
    private final String msg;
    private final T data;

    private R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // ==================== 成功 ====================

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return of(ResultCode.SUCCESS, data);
    }

    public static <T> R<T> ok(T data, String msg) {
        return new R<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    /** 成功且无 data，仅自定义提示语 */
    public static R<Void> okMsg(String msg) {
        return new R<>(ResultCode.SUCCESS.getCode(), msg, null);
    }

    // ==================== 失败 ====================

    public static <T> R<T> fail() {
        return fail(ResultCode.FAIL);
    }

    public static <T> R<T> fail(String msg) {
        return fail(ResultCode.FAIL.getCode(), msg);
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return new R<>(code, msg, null);
    }

    public static <T> R<T> fail(ResultCode resultCode) {
        return fail(resultCode.getCode(), resultCode.getMsg());
    }

    // ==================== 通用构建 ====================

    public static <T> R<T> of(ResultCode resultCode, T data) {
        return new R<>(resultCode.getCode(), resultCode.getMsg(), data);
    }

    public static <T> R<T> of(Integer code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    public boolean isSuccess() {
        return ResultCode.SUCCESS.getCode().equals(this.code);
    }
}

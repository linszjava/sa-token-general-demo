package com.size.springboot3.result;

import com.size.springboot3.exception.ResultCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/5/31 01:06
 */
@Data
@Accessors(chain = true)
public class R<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> ok(T data) {
        return innerR(data);
    }

    public static <T> R<T> ok() {
        return innerR(null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return innerR(data).setMsg(msg);
    }

    public static <T> R<T> fail() {
        return new R<T>()
                .setCode(500)
                .setMsg("操作失败")
                .setData(null);
    }

    public static <T> R<T> fail(String msg) {
        return fail(ResultCode.FAIL.getCode(), msg);  // 调用 43行的方法
    }
    public static <T> R<T> fail(Integer code, String msg) {
        return new R<T>().setCode(code).setMsg(msg).setData(null);
    }
    public static <T> R<T> fail(ResultCode resultCode) {
        return fail(resultCode.getCode(), resultCode.getMsg());
    }

    private static <T> R<T> innerR(T data) {
        R<T> r = new R<>();
        return r.setCode(200)
                .setMsg("操作成功")
                .setData(data);
    }
}

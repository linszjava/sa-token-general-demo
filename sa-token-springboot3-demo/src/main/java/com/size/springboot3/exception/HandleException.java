package com.size.springboot3.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.size.springboot3.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/5/31 02:14
 */
@Slf4j
@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(CommonException.class)
    public R<Void> handleCommonException(CommonException e) {
        log.error("异常信息：{}", e.getMessage());
        return new R<Void>().setCode(e.getCode()).setMsg(e.getMessage());
    }

    /** Sa-Token 未登录 */
    @ExceptionHandler(NotLoginException.class)
    public R<Void> handleNotLoginException(NotLoginException e) {
        return new R<Void>()
                .setCode(ResultCode.UNAUTHORIZED.getCode())
                .setMsg(ResultCode.UNAUTHORIZED.getMsg());
    }
    /** Sa-Token 无权限 / 无角色 */
    @ExceptionHandler({NotPermissionException.class, NotRoleException.class})
    public R<Void> handleSaTokenAuthException(RuntimeException e) {
        return new R<Void>()
                .setCode(ResultCode.FORBIDDEN.getCode())
                .setMsg(ResultCode.FORBIDDEN.getMsg());
    }
    /** 参数校验（@Valid）
     * R<Void>：明确告诉编译器和读代码的人——这次响应没有业务 data，比裸写 R 更规范。
     * void：方法关键字，表示方法无返回值。
     * Void：类，专门用在泛型里表示「无数据类型」，实际值就是 null
     *
     * */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public R<Void> handleValidException(Exception e) {
        String msg = "参数错误";
        if (e instanceof MethodArgumentNotValidException manve) {
            msg = manve.getBindingResult().getFieldError().getDefaultMessage();
        } else if (e instanceof BindException be) {
            msg = be.getBindingResult().getFieldError().getDefaultMessage();
        }
        return new R<Void>()
                .setCode(ResultCode.PARAM_ERROR.getCode())
                .setMsg(msg);
    }
    /** 兜底 */
    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e) {
        log.error("系统异常", e);
        return new R<Void>()
                .setCode(ResultCode.FAIL.getCode())
                .setMsg(ResultCode.FAIL.getMsg());
    }
}

/**
 *
 *  参数校验（@Valid）
 *      * R<Void>：明确告诉编译器和读代码的人——这次响应没有业务 data，比裸写 R 更规范。
 *      * void：方法关键字，表示方法无返回值。
 *      * Void：类，专门用在泛型里表示「无数据类型」，实际值就是 null
 *
 * R<Void> — 明确「没有业务数据」
 * public R<Void> handleValidException(Exception e) {
 *     return new R<Void>()
 *             .setCode(ResultCode.PARAM_ERROR.getCode())
 *             .setMsg(msg);
 *     // data 为 null，类型上表示「不携带业务数据」
 * }
 * 含义是：这次响应只有 code 和 msg，data 不需要、也不会有有效业务数据。
 *
 * 常见场景：异常处理、登出、删除成功等。
 *
 * 只写 R — 原始类型（Raw Type）
 * public R handleValidException(Exception e) {  // 不推荐
 *     return new R()...
 * }
 * 能编译、能跑，但：
 *
 * 丢掉了泛型信息，data 变成「任意类型」
 * IDE 和编译器会警告：R is a raw type
 * 失去类型检查，容易误用
 */
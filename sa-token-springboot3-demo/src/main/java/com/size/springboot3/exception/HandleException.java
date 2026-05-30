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
 * 全局异常处理
 *
 * @author linsz
 */
@Slf4j
@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(CommonException.class)
    public R<Void> handleCommonException(CommonException e) {
        log.warn("业务异常: {}", e.getMessage());
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public R<Void> handleNotLoginException(NotLoginException e) {
        return R.fail(ResultCode.UNAUTHORIZED);
    }

    @ExceptionHandler({NotPermissionException.class, NotRoleException.class})
    public R<Void> handleSaTokenAuthException(RuntimeException e) {
        return R.fail(ResultCode.FORBIDDEN);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public R<Void> handleValidException(Exception e) {
        String msg = ResultCode.PARAM_ERROR.getMsg();
        if (e instanceof MethodArgumentNotValidException manve) {
            msg = manve.getBindingResult().getFieldError().getDefaultMessage();
        } else if (e instanceof BindException be) {
            msg = be.getBindingResult().getFieldError().getDefaultMessage();
        }
        return R.fail(ResultCode.PARAM_ERROR.getCode(), msg);
    }

    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e) {
        log.error("系统异常", e);
        return R.fail(ResultCode.FAIL);
    }
}

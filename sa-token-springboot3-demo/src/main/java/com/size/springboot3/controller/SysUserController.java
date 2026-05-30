package com.size.springboot3.controller;

import com.size.springboot3.param.dto.LoginDto;
import com.size.springboot3.result.R;
import com.size.springboot3.service.SysUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/5/31 01:01
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 添加用户
     */
    @PostMapping("/add")
    public R<Boolean> addUser(@Valid LoginDto loginDto) {
        return R.ok(sysUserService.addUser(loginDto), "添加用户成功");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public R<Void> login(@Valid LoginDto loginDto) {
        sysUserService.login(loginDto);
        return R.okMsg("登录成功");
    }
}

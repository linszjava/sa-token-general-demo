package com.size.springboot3.controller;

import com.size.springboot3.param.dto.LoginDto;
import com.size.springboot3.service.SysUserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SysUserControllerTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void addUser() {
        sysUserService.addUser(new LoginDto("admin", "123456"));
        System.out.println("添加用户成功");
    }

    @Test
    public void login() {
        sysUserService.login(new LoginDto("admin", "123456"));
        System.out.println("登录成功");
    }
}
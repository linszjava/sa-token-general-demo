package com.size.springboot3.service;

import com.size.springboot3.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.size.springboot3.param.dto.LoginDto;

/**
* @author linsz
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2026-05-31 00:50:35
*/
public interface SysUserService extends IService<SysUser> {

    /**
     * 添加用户
     */
    boolean addUser(LoginDto loginDto);

    /**
     * 登录
     */
    void login(LoginDto loginDto);

    /**
     * 根据用户名查询用户ID
     */
    Long getUserIdByUserName(String userName);

}

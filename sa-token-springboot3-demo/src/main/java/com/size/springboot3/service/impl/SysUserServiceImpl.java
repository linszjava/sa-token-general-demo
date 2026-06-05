package com.size.springboot3.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.size.springboot3.entity.SysUser;
import com.size.springboot3.exception.CommonException;
import com.size.springboot3.exception.ResultCode;
import com.size.springboot3.param.dto.LoginDto;
import com.size.springboot3.service.SysUserService;
import com.size.springboot3.mapper.SysUserMapper;
import jakarta.annotation.Resource;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author linsz
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2026-05-31 00:50:35
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 添加用户
     *
     * @param loginDto
     */
    @Override
    public boolean addUser(LoginDto loginDto) {
        // 未实现 用户名已存在的判断
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(loginDto,sysUser);
        int result = sysUserMapper.insert(sysUser);
        if (result > 0) return true;
        else throw new CommonException(ResultCode.USER_EXISTS);
    }

    /**
     * 登录
     *
     * @param loginDto
     */
    @Override
    public void login(LoginDto loginDto) {
        Long userId = getUserIdByUserName(loginDto.getUsername());
        StpUtil.login(userId);

    }

    /**
     * 根据用户名查询用户ID
     *
     * @param userName
     */
    @Override
    public Long getUserIdByUserName(String userName) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>(SysUser.class);
        Long userId = sysUserMapper.selectOne(wrapper.eq(SysUser::getUsername, userName)).getId();
        if (userId != null) return userId;
        else throw new CommonException(ResultCode.USER_NOT_EXISTS);
    }
}





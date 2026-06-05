package com.size.springboot3.controller.up;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
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
 * @date 2026/6/6 00:09
 */
@RestController
@RequestMapping("/not-cookie/")
public class NotCookieController {

    /**
     * 登录成功后返回 token
     */

    @Resource
    private SysUserService sysUserService;

    @PostMapping("login")
    public R<SaTokenInfo> login(@Valid LoginDto loginDto) {
        sysUserService.login(loginDto);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return R.ok(tokenInfo);
        /**
         * {
         *   "code": 200,
         *   "msg": "操作成功",
         *   "data": {
         *     "tokenName": "size-token",
         *     "tokenValue": "e1e1f643-6800-4153-82cc-23a04ea8ec0f",
         *     "isLogin": true,
         *     "loginId": "1",
         *     "loginType": "login",
         *     "tokenTimeout": 2592000,
         *     "sessionTimeout": 2592000,
         *     "tokenSessionTimeout": -2,
         *     "tokenActiveTimeout": -1,
         *     "loginDeviceType": "DEF",
         *     "tag": null
         *   }
         * }
         */


    }
}

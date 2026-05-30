package com.size.springboot3.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.size.springboot3.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/5/31 04:58
 */
@RestController
@RequestMapping("/acc")
public class LoginController {

    /**
     * 查询登录状态
     */
    @GetMapping("/isLogin")
    public R<Boolean> isLogin() {
        return R.ok(StpUtil.isLogin());
    }

    /**
     * 查询登录信息
     *
     * {
     *   "code": 200,
     *   "msg": "操作成功",
     *   "data": {
     *     "tokenName": "size-token",
     *     "tokenValue": "206fa4da-7eab-4c17-8080-97d3d82bd25d",
     *     "isLogin": true,
     *     "loginId": "1",
     *     "loginType": "login",
     *     "tokenTimeout": 2588770,
     *     "sessionTimeout": 2588770,
     *     "tokenSessionTimeout": -2,
     *     "tokenActiveTimeout": -1,
     *     "loginDeviceType": "DEF",
     *     "tag": null
     *   }
     * }
     */
    @GetMapping("/getInfo")
    public R<SaTokenInfo> getInfo() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return R.ok(tokenInfo);
    }

    /**
     * 测试注销
     */
    @GetMapping("/logout")
    public R<Void> logout() {
        StpUtil.logout();
        return R.ok();
    }
}

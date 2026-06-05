package com.size.springboot3.controller.up;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.size.springboot3.param.dto.LoginWithTypeDto;
import com.size.springboot3.result.R;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/6/6 00:33
 *  复现 登录 即不允许同一设备同时在线  注意yml中的 isConcurrent=false
 */
@RestController
@RequestMapping("/mutex-login/")
public class MutexLoginController {

//    @PostMapping("/login-with-type")
    @SaIgnore
    @GetMapping("/login-with-type")
    public R loginWithType(@Valid LoginWithTypeDto loginWithTypeDto) {
        StpUtil.login(loginWithTypeDto.getLoginId(), loginWithTypeDto.getLoginType());
        return R.ok();
    }

    /**
     *
     *
     * code	200
     * msg	"操作成功"
     * data	"当前设备是否登录：true  当前设备：PC"
     * @return
     *
     * {
     * "code": 200,
     * "msg": "操作成功",
     * "data": "当前设备是否登录：false  当前设备：null"
     * }
     */
    @SaIgnore
    @GetMapping("/is-login")
    public R isLogin() {
        String str = "当前设备是否登录：" + StpUtil.isLogin() + "  当前设备：" + StpUtil.getLoginDeviceType();
        return R.ok(str);
    }
}

package com.size.springboot3.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.size.springboot3.entity.SysUser;
import com.size.springboot3.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/6/5 00:20
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    /**
     * get simple value from session
     */
    @GetMapping("/set-value")
    public R<Void> setValue() {
        SaSession session = StpUtil.getSession();
        session.set("name","linsz")
                .setId("1000-1");
        return R.ok();
    }

    @GetMapping("/get-value")
    public R<Map<String, Object>> getValue() {
        SaSession session = StpUtil.getSession();
        String name = (String) session.get("name");
        String id = session.getId();
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("id", id);
        return R.ok(result);
    }

    /**
     * get model value from session
     */
    @GetMapping("/set-model-value")
    public R<SysUser> setModelValue() {
        SaSession session = StpUtil.getSession();
        session.set("user", new SysUser(2L, "linsz", "123456"));
        //我们就可以在任意代码处获取这个 user 了
        SysUser user = session.getModel("user", SysUser.class);
        return R.ok(user);
    }
    /**
     * get token Session
     *
     * {
     *   "code": 200,
     *   "msg": "操作成功",
     *   "data": {
     *     "id": "size-token:login:token-session:46ac3030-59bc-4559-8453-fe8e7e97e162",
     *     "type": "Token-Session",
     *     "loginType": "login",
     *     "loginId": null,
     *     "token": "46ac3030-59bc-4559-8453-fe8e7e97e162",
     *     "historyTerminalCount": 0,
     *     "createTime": 1780590799830,
     *     "dataMap": {},
     *     "terminalList": []
     *   }
     * }
     */
    @GetMapping("/get-token-session")
    public R<SaSession> getTokenSession() {
        SaSession tokenSession = StpUtil.getTokenSession();
        return R.ok(tokenSession);
    }

    /**
     * custom session
     */
    @GetMapping("/set-custom-session")
    public R<SaSession> setCustomSession() {
        SaSession sa = SaSessionCustomUtil.getSessionById("224",false);
        sa.set("name","zhangfei");
        return R.ok();
    }

    @GetMapping("/get-custom-session")
    public R<String> getCustomSession() {
        SaSession sa = SaSessionCustomUtil.getSessionById("224");
        String name = (String) sa.get("name");
        return R.ok(name);
    }


}

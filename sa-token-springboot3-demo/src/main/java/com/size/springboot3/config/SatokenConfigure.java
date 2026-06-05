package com.size.springboot3.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaFoxUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/6/1 02:37
 */
@Configuration
public class SatokenConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new SaInterceptor(handle -> {
                    // StpUtil.checkLogin()
                    // 自定义完善内容 方法体为一个lamba 表达式
                    SaRouter.match("/**")
                            .notMatch("/sys-user/login")
                            .check( r-> StpUtil.checkLogin());

                    // 根据模块来划分 不同模块不同鉴权
                    SaRouter.match("/sys-user/**", r-> StpUtil.checkPermission("sys-user"));
                    SaRouter.match("/sys-role/**", r-> StpUtil.checkPermission("sys-role"));
//                    SaRouter.match("/sys-role/**", r-> StpUtil.checkPermission("sys-role")).stop();
                    // 以此类推
//            SaRouter.match(SaHttpMethod.ALL).check(r-> StpUtil.checkLogin());
//            SaRouter.match(SaHttpMethod.GET).match("/sys-user/**").check(r-> StpUtil.checkPermission("sys-user"));

                }




                ))
                .addPathPatterns("/**")
                .excludePathPatterns("/sys-user/login");
    }

    /**
     * 自定义 satoken  如果那些yml中配置好的那些uuid simple-uuid你看不上 你可以自定义如下
     * 详见  SaStrategy.instance.createToken 类的介绍
     *
     * {
     *   "code": 200,
     *   "msg": "操作成功",
     *   "data": {
     *     "tokenName": "size-token",
     *     "tokenValue": "efdSDtfwqgkSxSi6XLdIEd0Zc257iquYul7DdafQItvg8BO4tdyyaaOUrSIA",
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
    @PostConstruct
    public void satokenStrategy() {
        SaStrategy.instance.createToken = (loginId, loginType) ->
                SaFoxUtil.getRandomString(60);
    }
}

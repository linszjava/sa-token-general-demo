package com.size.springboot3.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
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
}

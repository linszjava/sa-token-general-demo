package com.size.springboot3;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/5/30 23:59
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.size.springboot3.mapper")
public class SatokenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatokenDemoApplication.class, args);
        log.info("启动成功,Satoken的配置如下", SaManager.getConfig());
    }
}

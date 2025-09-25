package com.lyhm.lyhmaicodeplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
@MapperScan("com.lyhm.lyhmaicodeplatform.mapper")
public class LyhmAiCodePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyhmAiCodePlatformApplication.class, args);
    }

}

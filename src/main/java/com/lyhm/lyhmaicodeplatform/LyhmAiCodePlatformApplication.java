package com.lyhm.lyhmaicodeplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class LyhmAiCodePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyhmAiCodePlatformApplication.class, args);
    }

}

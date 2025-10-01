package com.lyhm.lyhmaicodeplatform;

import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(exclude = {RedisEmbeddingStoreAutoConfiguration.class})

@MapperScan("com.lyhm.lyhmaicodeplatform.mapper")
public class LyhmAiCodePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyhmAiCodePlatformApplication.class, args);
    }

}

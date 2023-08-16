package com.binbinxiu.whh.jiudao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = "com.binbinxiu.whh.jiudao.mapper")
public class JiuDaoFluxApplication {
    public static void main(String[] args) {
        SpringApplication.run(JiuDaoFluxApplication.class, args);
    }
}

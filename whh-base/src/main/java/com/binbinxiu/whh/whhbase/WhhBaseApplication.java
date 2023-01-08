package com.binbinxiu.whh.whhbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScans;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.binbinxiu.whh")
public class WhhBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhhBaseApplication.class, args);
    }

}

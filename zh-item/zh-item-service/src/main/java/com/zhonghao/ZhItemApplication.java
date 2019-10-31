package com.zhonghao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZhItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhItemApplication.class);
    }
}

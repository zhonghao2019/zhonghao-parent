package com.zhonghao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
// 包扫描
@MapperScan("com.zhonghao.item.mapper")
public class ZhItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhItemApplication.class);
    }
}

package com.caroLe.manager.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author CaroLe
 * @Date 2023/4/20 17:40
 * @Description
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ManagerAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerAuthApplication.class, args);
    }
}

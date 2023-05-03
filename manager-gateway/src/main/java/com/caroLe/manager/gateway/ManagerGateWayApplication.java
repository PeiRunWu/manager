package com.caroLe.manager.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CaroLe
 * @Date 2023/4/20 21:34
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ManagerGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerGateWayApplication.class, args);
    }
}

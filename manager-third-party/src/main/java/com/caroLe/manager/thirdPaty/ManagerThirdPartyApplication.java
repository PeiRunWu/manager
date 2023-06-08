package com.caroLe.manager.thirdPaty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CaroLe
 * @Date 2023/6/8 21:23
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ManagerThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerThirdPartyApplication.class, args);
    }
}

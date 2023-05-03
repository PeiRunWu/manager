package com.caroLe.manager.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:54
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.caroLe.manager")
@MapperScan("com.caroLe.manager.repository.dao.system")
public class ManagerSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerSystemApplication.class, args);
    }

}

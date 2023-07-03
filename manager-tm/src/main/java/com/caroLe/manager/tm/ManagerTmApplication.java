package com.caroLe.manager.tm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CaroLe
 * @Date 2023/7/2 21:57
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagerServer
public class ManagerTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerTmApplication.class, args);
    }
}

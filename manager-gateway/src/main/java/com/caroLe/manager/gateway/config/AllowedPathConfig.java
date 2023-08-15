package com.caroLe.manager.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author CaroLe
 * @Date 2023/4/22 14:36
 * @Description
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.allowed")
public class AllowedPathConfig {

    private List<String> paths;
}

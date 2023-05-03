package com.caroLe.manager.gateway.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/22 14:36
 * @Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "security.allowed")
public class AllowedPathConfig {

    private List<String> paths;
}

package com.caroLe.manager.thirdPaty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author CaroLe
 * @Date 2023/7/24 21:44
 * @Description
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "thread")
public class ThreadPoolConfigProperties {
    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;
}

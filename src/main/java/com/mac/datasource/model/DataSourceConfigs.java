package com.mac.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @program: macboot
 * @author: mac
 * @create: 2024-08-19 20:11
 */
@Configuration
@ConfigurationProperties(
    prefix = "spring.datasource"
)
@Data
public class DataSourceConfigs {
    private LinkedHashMap<String, DataSourceInfo> dbs;
}

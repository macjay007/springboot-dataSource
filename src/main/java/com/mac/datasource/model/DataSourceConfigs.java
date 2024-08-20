package com.mac.datasource.model;

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
public class DataSourceConfigs {
    private LinkedHashMap<String, DataSourceInfo> dbs;

    public DataSourceConfigs() {
    }

    public LinkedHashMap<String, DataSourceInfo> getDbs() {
        return this.dbs;
    }

    public void setDbs(final LinkedHashMap<String, DataSourceInfo> dbs) {
        this.dbs = dbs;
    }

}

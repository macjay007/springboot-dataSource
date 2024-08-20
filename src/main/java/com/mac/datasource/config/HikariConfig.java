package com.mac.datasource.config;

import com.mac.datasource.model.DataSourceConfigs;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: macboot
 * @author: mac
 * @create: 2024-08-19 20:07
 */
@Configuration
public class HikariConfig {
    @Value("${spring.datasource.primary:empty}")
    private String primary;

    public HikariConfig() {
    }

    @Bean(
        name = {"dynamicDataSource"}
    )
    public DynamicDataSource dataSource(DataSourceConfigs dataSourceConfigs, HikariProperties properties) {
        Map<Object, Object> targetDataSources = new LinkedHashMap();
        AtomicReference<HikariDataSource> defaultDataSource = new AtomicReference();
        dataSourceConfigs.getDbs().forEach((key, dataSourceInfo) -> {
            HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder.create().type(HikariDataSource.class).url(dataSourceInfo.getJdbcUrl()).username(dataSourceInfo.getUsername()).password(dataSourceInfo.getPassword()).driverClassName(dataSourceInfo.getDriverClassName()).build();
            HikariDataSource hikariDataSource = properties.dataSource(key, dataSource);
            targetDataSources.put(key, hikariDataSource);
            if (key.equals(this.primary)) {
                defaultDataSource.set(hikariDataSource);
            }

        });
        return defaultDataSource.get() == null ? new DynamicDataSource((DataSource)targetDataSources.values().stream().findFirst().orElse((Object)null), targetDataSources) : new DynamicDataSource((DataSource)defaultDataSource.get(), targetDataSources);
    }

    @Bean(
        name = {"transactionManager"}
    )
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}

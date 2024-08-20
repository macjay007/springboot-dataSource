package com.mac.datasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: macboot
 * @author: mac
 * @create: 2024-08-19 20:10
 */
@Configuration
public class HikariProperties {
    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minIdle;
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maxPoolSize;
    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;
    @Value("${spring.datasource.hikari.auto-commit}")
    private boolean autoCommit;
    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;

    public HikariProperties() {
    }

    public HikariDataSource dataSource(String dbName, HikariDataSource dataSource) {
        dataSource.setConnectionTimeout((long)this.connectionTimeout);
        dataSource.setIdleTimeout((long)this.idleTimeout);
        dataSource.setMaximumPoolSize(this.maxPoolSize);
        dataSource.setAutoCommit(this.autoCommit);
        dataSource.setMinimumIdle(this.minIdle);
        dataSource.setPoolName("HikariPool-" + dbName);
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "20480");
        dataSource.addDataSourceProperty("useServerPrepStmts", "true");
        dataSource.addDataSourceProperty("useLocalSessionState", "true");
        dataSource.addDataSourceProperty("useLocalTransactionState", "true");
        dataSource.addDataSourceProperty("rewriteBatchedStatements", "true");
        dataSource.addDataSourceProperty("cacheResultSetMetadata", "true");
        dataSource.addDataSourceProperty("cacheServerConfiguration", "true");
        dataSource.addDataSourceProperty("elideSetAutoCommits", "true");
        dataSource.addDataSourceProperty("maintainTimeStats", "false");
        return dataSource;
    }
}

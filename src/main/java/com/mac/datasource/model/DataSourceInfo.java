package com.mac.datasource.model;

import lombok.Data;

/**
 * @program: macboot
 * @author: mac
 * @create: 2024-08-19 20:16
 */
@Data
public class DataSourceInfo {
    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;
}

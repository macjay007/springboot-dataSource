package com.mac.datasource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: macboot
 * @author: mac
 * @create: 2024-08-19 19:55
 */
public class DynamicDataSourceContextHolder {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal();

    public DynamicDataSourceContextHolder() {
    }

    public static String getDataSourceType() {
        return (String)CONTEXT_HOLDER.get();
    }

    public static void setDataSourceType(String dsType) {
        CONTEXT_HOLDER.set(dsType);
        log.debug("连接到{}数据源", dsType);
    }

    public static void clearDataSourceType() {
        log.debug("清空数据源变量");
        CONTEXT_HOLDER.remove();
    }
}

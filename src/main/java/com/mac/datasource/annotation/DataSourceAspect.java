package com.mac.datasource.annotation;

import com.mac.datasource.config.DynamicDataSourceContextHolder;
import com.mac.datasource.model.DataSourceConfigs;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: mac
 * @date: 2024/8/19
 */
@Aspect
@Component
@Order(1)
public class DataSourceAspect {
    private static final Logger log = LoggerFactory.getLogger(DataSourceAspect.class);
    @Resource
    private DataSourceConfigs dataSourceConfigs;

    public DataSourceAspect() {
    }

    @Pointcut("@annotation(com.mac.datasource.annotation.DB)")
    public void doPointCut() {
    }

    @Around("doPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DB dataSource = this.getDataSource(point);
        if (!Objects.isNull(dataSource)) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
        }

        Object var3;
        try {
            var3 = point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceType();
        }

        return var3;
    }

    public DB getDataSource(ProceedingJoinPoint point) {
        Class<?> className = point.getTarget().getClass();
        if (className.isAnnotationPresent(DB.class)) {
            return (DB)className.getAnnotation(DB.class);
        } else {
            Method method = ((MethodSignature)point.getSignature()).getMethod();
            return (DB)method.getAnnotation(DB.class);
        }
    }
}


package com.example.taobao.inventory.webservice.aop;

import com.example.taobao.common.annotation.DS;
import com.example.taobao.common.datasource.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DynamicDataSourceAop {

    @Before("@annotation(ds)")
    public void beforeSwitchDs(JoinPoint point,DS ds) {
        Class<?> className = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String datasourceId = null;
        try {
            Method method = className.getMethod(methodName, argClass);
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                datasourceId = annotation.id();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (datasourceId == null)
            datasourceId = DataSourceContextHolder.DEFAULT_DATASOURCE_ID;

        DataSourceContextHolder.setDataSourceId(datasourceId);
    }

    @After("@annotation(ds)")
    public void afterSwitchDS(JoinPoint point,DS ds) {
        DataSourceContextHolder.clearDataSourceId();
    }
}

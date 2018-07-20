package com.example.test.springinjecttest.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;

import static org.springframework.context.annotation.AnnotationConfigUtils.registerAnnotationConfigProcessors;

public class RpcClassPathScanning extends ClassPathBeanDefinitionScanner {


    public RpcClassPathScanning(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, @Nullable ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
        setEnvironment(environment);
        setResourceLoader(resourceLoader);
        //registerAnnotationConfigProcessors(registry);
    }
}

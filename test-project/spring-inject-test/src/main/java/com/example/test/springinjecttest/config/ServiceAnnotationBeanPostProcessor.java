package com.example.test.springinjecttest.config;

import com.example.test.springinjecttest.annotation.Service;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

@Component
public class ServiceAnnotationBeanPostProcessor implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware, EnvironmentAware, BeanClassLoaderAware {

    private Environment environment;
    private ResourceLoader resourceLoader;
    private ClassLoader classLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Set<String> scanPath = getScanPath();
        if (scanPath.size() == 0)
            return;
        RpcClassPathScanning scanning = new RpcClassPathScanning(registry, false, this.environment, this.resourceLoader);
        scanning.addIncludeFilter(new AnnotationTypeFilter(Service.class));
        for (String path : scanPath) {
            //这个方法会自动扫描加了Service注解的类，并把BeanDefinition信息注入
            scanning.scan(path);
            //dubbo会再重新以ServiceBean 作为RootBeanDefinition 把相关接口信息，类名，重命名后注入容器
            Set<BeanDefinition> candidateComponents = scanning.findCandidateComponents(path);
            for (BeanDefinition beanDefinition : candidateComponents) {
                registryBeanDefinition(beanDefinition, scanning, registry);
            }
        }

    }

    private void registryBeanDefinition(BeanDefinition beanDefinition, RpcClassPathScanning scanning, BeanDefinitionRegistry registry) {
        String beanName = beanDefinition.getBeanClassName();
        Service service = findAnnotation(beanName, Service.class);
        //这里模拟测试下这个动态注入技术，dubbo是注入的ServiceBean
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(RootClass.class);
        //这里dubbo是根据protocol名字，注入的bean
        //beanDefinitionBuilder.addPropertyValue("protocol", service.protocol());
        if (StringUtils.hasText(service.version()))
            beanDefinitionBuilder.addPropertyValue("version", service.version());
        //这些属性dubbo是从配置文件读取的属性
        if (StringUtils.hasText(service.application()))
            beanDefinitionBuilder.addPropertyValue("application", service.application());
        if (service.registry().length > 0)
            beanDefinitionBuilder.addPropertyValue("registry", service.registry());
        AbstractBeanDefinition beanDefinition1 = beanDefinitionBuilder.getBeanDefinition();
        //dubbo是重写了bean名字
        beanName = "rpc#" + beanName;
        registry.registerBeanDefinition(beanName, beanDefinition1);
    }

    private Service findAnnotation(String beanName, Class<Service> serviceClass) {
        //通过spring提供的两个工具类找到注解
        Class beanClass = ClassUtils.resolveClassName(beanName, this.classLoader);
        return AnnotationUtils.findAnnotation(beanClass, serviceClass);
    }

    private Set<String> getScanPath() {
        Set<String> scanPath = new HashSet<>(4);
        String property = this.environment.getProperty("rpc.scan");
        if (property == null)
            throw new IllegalStateException("扫描路径不能为空!");
        String[] array = property.split(",");
        for (String path : array) {
            scanPath.add(path);
        }
        return scanPath;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;

    }
}

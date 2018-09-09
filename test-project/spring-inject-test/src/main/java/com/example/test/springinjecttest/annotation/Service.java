package com.example.test.springinjecttest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *自定义Service注解，扫描所有被service注解的类,并注入容器
 *@author lh
 *@date 2018/7/19
 *@since
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    String[] protocol() default {};

    String application() default "";

    String version() default "";

    String[] registry() default {};
}

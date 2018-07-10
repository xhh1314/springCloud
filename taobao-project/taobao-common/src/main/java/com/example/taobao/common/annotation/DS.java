package com.example.taobao.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {

    /**
     * 数据源的id
     *
     * @param
     * @date 2018/5/15
     * @author lh
     * @since
     */
    String id() default "datasource0";
}

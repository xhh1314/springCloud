package com.example.taobao.common.datasource;

/**
 * 存放数据源id的ThreadLocal
 *
 * @author lh
 * @date 2018/5/15
 * @since
 */
public class DataSourceContextHolder {

    public static final String DEFAULT_DATASOURCE_ID = "datasource0";

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();


    public static void setDataSourceId(String dataSourceId) {
        threadLocal.set(dataSourceId);
    }

    public static String getDataSourceId() {
        return threadLocal.get();
    }

    public static void clearDataSourceId() {
        threadLocal.remove();
    }
}
/*
* 顺便说明下ThreadLocal的原理：
* 每个Thread都一个一个存放变量的map，而treadLocal实例就是这个map的key,所以每个ThreadLocal实例只能对应一个value，如果要在一个线程中保存多个value，则可以创建多个threadLocal实例进行设定
* */

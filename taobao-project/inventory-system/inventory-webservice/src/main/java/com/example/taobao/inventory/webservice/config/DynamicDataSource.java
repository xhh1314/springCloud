package com.example.taobao.inventory.webservice.config;


import com.example.taobao.common.datasource.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceId();
    }
}

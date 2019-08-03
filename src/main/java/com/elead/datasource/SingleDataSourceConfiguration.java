package com.elead.datasource;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 单数据源配置
 * @author Administrator
 */
@Configuration
public class SingleDataSourceConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(SingleDataSourceConfiguration.class);
    @Bean
    @ConfigurationProperties(prefix = "jdbc.ds")
    public DataSource readDataSource() {
    	System.err.println("单数据源配置加载成功!");
    	logger.info("OK!  单数据源配置加载成功!");
        return new DruidDataSource();
    }
}

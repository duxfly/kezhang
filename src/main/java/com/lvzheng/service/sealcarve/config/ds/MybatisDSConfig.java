package com.lvzheng.service.sealcarve.config.ds;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MybatisDSConfig {

    /*** data source ***/

    @Bean(name = "jebe")
    @ConfigurationProperties(prefix = "spring.datasource.dbwwwjebecom") // application.properteis中对应属性的前缀
    public DataSource dataSourceJEBE() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "lvzheng")
    @ConfigurationProperties(prefix = "spring.datasource.lvzheng")
    public DataSource dataSourceLVZheng() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "enterprise")
    @ConfigurationProperties(prefix = "spring.datasource.enterprise")
    public DataSource dataSourceEnterprise() {
        return DataSourceBuilder.create().build();
    }



    /*** mapper ***/

    /**
     * create MapperScannerConfigurer
     * @return MapperScannerConfigurer
     */
    @Bean(name = "jebeMapperScanner")
    public MapperScannerConfigurer jebeMapperScanner() {
        MapperScannerConfigurer conf = new MapperScannerConfigurer();
        conf.setBasePackage("com.lvzheng.service.sealcarve.mapper");
        conf.setSqlSessionFactoryBeanName("sqlSessionFactoryJEBE");
        return conf;
    }

    /**
     * create MapperScannerConfigurer
     * @return MapperScannerConfigurer
     */
    @Bean(name = "enterpriseMapperScanner")
    public MapperScannerConfigurer enterpriseMapperScanner() {
        MapperScannerConfigurer conf = new MapperScannerConfigurer();
        conf.setBasePackage("com.lvzheng.service.sealcarve.jebe.mapper.enterprise");
        conf.setSqlSessionFactoryBeanName("sqlSessionFactoryEnterprise");
        return conf;
    }

    /**
     * create MapperScannerConfigurer
     * @return MapperScannerConfigurer
     */
    @Bean(name = "lvzhengMapperScanner")
    public MapperScannerConfigurer lvzhengMapperScanner() {
        MapperScannerConfigurer conf = new MapperScannerConfigurer();
        conf.setBasePackage("com.lvzheng.service.sealcarve.jebe.mapper.lvzheng");
        conf.setSqlSessionFactoryBeanName("sqlSessionFactoryLVZheng");
        return conf;
    }

    /**
     * create MapperScannerConfigurer
     * @return MapperScannerConfigurer
     */
    @Bean(name = "customerMapperScanner")
    public MapperScannerConfigurer customerMapperScanner() {
        MapperScannerConfigurer conf = new MapperScannerConfigurer();
        conf.setBasePackage("com.lvzheng.service.sealcarve.jebe.mapper.customer");
        conf.setSqlSessionFactoryBeanName("sqlSessionFactoryJEBE");
        return conf;
    }

}

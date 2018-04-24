package com.lvzheng.service.sealcarve.config.ds;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LVZhengDB extends DBBase {

    @Autowired
    @Qualifier("lvzheng")
    private DataSource lvzhengDS;

    /**
     *
     * @return SqlSessionFactory
     * @throws Exception error
     */
    @Bean(name = "sqlSessionFactoryLVZheng")
    public SqlSessionFactory sqlSessionFactoryLVZheng() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(lvzhengDS);
        factoryBean.setConfiguration(getConfiguration());
        return factoryBean.getObject();
    }

    /**
     *
     * @return SqlSessionTemplate
     * @throws Exception error
     */
    @Bean(name = "sqlSessionTemplateLVZheng")
    public SqlSessionTemplate sqlSessionTemplateLVZheng() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryLVZheng());
        return template;
    }
}

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
public class EnterpriseDB extends DBBase {

    @Autowired
    @Qualifier("enterprise")
    private DataSource enterpriseDS;

    /**
     *
     * @return SqlSessionFactory
     * @throws Exception error
     */
    @Bean(name = "sqlSessionFactoryEnterprise")
    public SqlSessionFactory sqlSessionFactoryEnterprise() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(enterpriseDS);
        factoryBean.setConfiguration(getConfiguration());
        return factoryBean.getObject();
    }

    /**
     *
     * @return SqlSessionTemplate
     * @throws Exception error
     */
    @Bean(name = "sqlSessionTemplateEnterprise")
    public SqlSessionTemplate sqlSessionTemplateEnterprise() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryEnterprise()); // 使用上面配置的Factory
        return template;
    }
}

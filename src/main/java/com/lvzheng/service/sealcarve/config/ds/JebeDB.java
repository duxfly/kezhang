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
public class JebeDB extends DBBase {

    @Autowired
    @Qualifier("jebe")
    private DataSource jebeDS;

    /**
     *
     * @return SqlSessionFactory
     * @throws Exception error
     */
    @
            Bean(name = "sqlSessionFactoryJEBE")
    public SqlSessionFactory sqlSessionFactoryJEBE() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(jebeDS);
        factoryBean.setConfiguration(getConfiguration());
        return factoryBean.getObject();
    }

    /**
     *
     * @return SqlSessionTemplate
     * @throws Exception error
     */
    @Bean(name = "sqlSessionTemplateJEBE")
    public SqlSessionTemplate sqlSessionTemplateJEBE() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryJEBE()); // 使用上面配置的Factory
        return template;
    }
}

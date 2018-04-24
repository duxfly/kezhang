package com.lvzheng.service.sealcarve.config.ds;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.Configuration;

public abstract class DBBase {

    /**
     * 生成mybatis Configuration
     * @return
     */
    public Configuration getConfiguration() {
        Configuration conf = new Configuration();
        conf.setMapUnderscoreToCamelCase(true);
        conf.setLogPrefix("LOG4J");
        LogFactory.useLog4JLogging();

        return conf;
    }
}

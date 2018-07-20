
package com.zt.study.conf;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.UUID;

/**
 * @author linwbai
 * @version $Id: SqlSessionConfig.java, v 0.1 2018-01-16 下午2:27 linwbai Exp $$
 */
@Configuration
@Import(DynamicPlugin.class)
public class SqlSessionConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public Properties properties(){
        Properties properties= new Properties();
        convertProperties(properties);
        return  properties;
    }

    @Bean(name = "dataSourceRead")
    @ConfigurationProperties(prefix = "spring.datasourceread")
    public Properties dataSourceRead() throws Exception {
        Properties properties= new Properties();
        properties.putAll(properties());
        convertProperties(properties);
        return  properties;
    }

    @Bean(name = "dataSourceWrite")
    @ConfigurationProperties(prefix = "spring.datasourcewrite")
    public Properties dataSourceWrite() throws Exception {
        Properties properties= new Properties();
        properties.putAll(properties());
        convertProperties(properties);
        return  properties;
    }


    /**
     @Bean(name = "dataSourceBasRead")
     @ConfigurationProperties(prefix = "spring.dataSourceBasRead")
     public Properties dataSourceBasRead() throws Exception {
     Properties properties= new Properties();
     properties.putAll(properties());
     convertProperties(properties);
     return  properties;
     }

     @Bean(name = "dataSourceBasWrite")
     @ConfigurationProperties(prefix = "spring.dataSourceBasWrite")
     public Properties dataSourceBasWrite() throws Exception {
     Properties properties= new Properties();
     properties.putAll(properties());
     convertProperties(properties);
     return  properties;
     }
     **/

    @Bean
    @Qualifier("dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource(Properties dataSourceRead, Properties dataSourceWrite) throws Exception {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setReadDataSource(DruidDataSourceFactory.createDataSource(dataSourceRead));
        dynamicDataSource.setWriteDataSource(DruidDataSourceFactory.createDataSource(dataSourceWrite));
        return dynamicDataSource;
    }

    /**
     @Bean
     @Qualifier("dynamicDataSourceBas")
     public DataSource dynamicDataSourceBas(Properties dataSourceBasRead, Properties dataSourceBasWrite) throws Exception {
     DynamicDataSource dynamicDataSource = new DynamicDataSource();
     dynamicDataSource.setReadDataSource(DruidDataSourceFactory.createDataSource(dataSourceBasRead));
     dynamicDataSource.setWriteDataSource(DruidDataSourceFactory.createDataSource(dataSourceBasWrite));
     return dynamicDataSource;
     }
     **/

    @Bean
    @Scope("prototype")
    @ConfigurationProperties(prefix = "spring.mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfiguration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //configuration.getTypeHandlerRegistry().register(UUID.class,UUIDTypeHandler.class);
        return configuration;
    }

    private SqlSessionFactory sqlSessionFactoryBean(org.apache.ibatis.session.Configuration mybatisConfiguration,
                                                    DataSource druidDataSource, DynamicPlugin dynamicPlugin) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource);
        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        //注册拦截器 -- zhutong
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{dynamicPlugin});
        //        mybatisConfiguration.getTypeHandlerRegistry().register(UUID.class,ObjectTypeHandler.class);
        //        mybatisConfiguration.getTypeHandlerRegistry().register(JsonArray.class,JsonHandler.class);
        //        mybatisConfiguration.getTypeHandlerRegistry().register(JsonObject.class,JsonHandler.class);
        //        ObjectTypeHandler handler = new ObjectTypeHandler();
        //        sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{handler});
        //        sqlSessionFactoryBean.setTypeHandlersPackage("com.choice.micro.ipos.dao.dao.base");
        //        sqlSessionFactoryBean.setTypeAliasesPackage("com.choice.micro.ipos.dao.entity");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mybatis/**/*Mapper.xml"));
       // sqlSessionFactoryBean.setPlugins(new Interceptor[]{dynamicPlugin,new JdbcTypeInterceptor()});
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionFactoryScm")
    @Primary
    public SqlSessionFactory sqlSessionFactoryScm(org.apache.ibatis.session.Configuration mybatisConfiguration, @Qualifier("dynamicDataSource") DataSource dataSourceScm, DynamicPlugin dynamicPlugin) throws Exception {
        return sqlSessionFactoryBean(mybatisConfiguration, dataSourceScm, dynamicPlugin);
    }

    @Bean(name = "sqlSessionTemplateScm")
    public SqlSessionTemplate baseSqlSessionTemplate(@Qualifier("sqlSessionFactoryScm") SqlSessionFactory sqlSessionFactoryScm) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryScm);
    }

    /**
     @Bean(name = "sqlSessionFactoryBas")
     public SqlSessionFactory sqlSessionFactoryBas(org.apache.ibatis.session.Configuration mybatisConfiguration, @Qualifier("dynamicDataSourceBas") DataSource dataSourceBas, DynamicPlugin dynamicPlugin) throws Exception {
     return sqlSessionFactoryBean(mybatisConfiguration, dataSourceBas, dynamicPlugin);
     }
     **/

    @Bean
    @ConditionalOnMissingBean
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSourceScm) {
        return new DynamicDataSourceTransactionManager(dataSourceScm);
    }

    /**
     @Bean
     public JdbcTemplate jdbcTemplateMete(Properties dataSourceBasRead) throws Exception {
     return new JdbcTemplate(DruidDataSourceFactory.createDataSource(dataSourceBasRead));
     }
     **/

    private void convertProperties(Properties properties){
        properties.forEach((k,v)-> properties.setProperty(k.toString(),String.valueOf(v)));
    }
}

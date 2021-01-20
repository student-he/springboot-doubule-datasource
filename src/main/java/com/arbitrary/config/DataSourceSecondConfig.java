package com.arbitrary.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Classname DataSourceSecondConfig
 * @Description 数据库为slave_test_database的数据源配置类，其余的数据库类似，非主数据库不需加上@Primary注解
 */
@Configuration
@MapperScan(basePackages = "com.arbitrary.dao.slave",sqlSessionTemplateRef = "secondSqlSessionTemplate")
public class DataSourceSecondConfig {
    //mapper扫描xml文件的路径
    final static String SECOND_DATASOURCE_MAPPER_LOCATION = "classpath:mapper/slave/*.xml";

    /**
     * 配置数据源
     * @return
     */
    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(SECOND_DATASOURCE_MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器
     * @param dataSource
     * @return
     */
    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager secondTransactionManager(@Qualifier("secondDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 结合类注解MapperScan配置MapperScannerConfigurer
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

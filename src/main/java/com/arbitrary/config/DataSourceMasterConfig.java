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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Classname DataSourceMasterConfig
 * @Description 数据库为master_test_database的数据源配置类，由于必须有一个作为主库，所以主库时必须加上@Primary注解
 */
@Configuration
@MapperScan(basePackages = "com.arbitrary.dao.master",sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class DataSourceMasterConfig {

    //mapper扫描xml文件的路径
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    /**
     * 配置数据源
     * primary是设置优先，因为有多个数据源，在没有明确指定用哪个的情况下，会用带有primary的，这个注解必须有一个数据源要添加
     */
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器
     * @param dataSource
     * @return
     */
    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 结合类注解MapperScan配置MapperScannerConfigurer
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

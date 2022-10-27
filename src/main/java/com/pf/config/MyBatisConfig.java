package com.pf.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author yk_xing
 */
@Configuration
@MapperScan("com.pf.dao.mapper")
public class MyBatisConfig {

    //private static final String MAPPER_LOCATION = "classpath*:/xml/**/*.xml";
    private static final String MAPPER_LOCATION = "classpath*:/com/pf/dao/**/*Mapper.xml";

    private static final String TYPE_ALIASES_PACKAGE = "com.pf.dao.entity";

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
            throws Exception {
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        GlobalConfig.DbConfig dbConfig = globalConfig.getDbConfig();
        dbConfig.setCapitalMode(true); //均大写
        dbConfig.setTablePrefix("T_");
        sessionFactory.setGlobalConfig(globalConfig);
        sessionFactory.setDataSource(dataSource);
        //实体扫描
        sessionFactory.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        //mapper-locations
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        sessionFactory.setPlugins(new Interceptor[]{interceptor});
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setLogImpl(Slf4jImpl.class);
        sessionFactory.setConfiguration(mybatisConfiguration);
        return sessionFactory.getObject();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 乐观锁
     *
     * @return
     */
   /* @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    *//**
     * 分页插件
     *//*
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }*/
}

package com.chinasoft.actualcombattable.actualcombattable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration //表示当前类是一个配置类，作用类似于Spring框架中的xml配置文件
@EnableJpaRepositories(basePackages = "com.chinasoft.actualcombattable.actualcombattable.repositories")
//指定Repository类的包路径
public class JpaConfiguration {
    //  1.DataSource
    @Bean //告诉Spring框架这个方法的返回值需要注册成Spring中的Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://rm-2ze4rc08sin9bgb346o.mysql.rds.aliyuncs.com:3308/sxkkrs_722?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
        dataSource.setUsername("sunxk_krs722");
        dataSource.setPassword("Sunxk863");
        return dataSource;
    }

    //  2.LocalContainerEntityManagerFactoryBean
//    管理EntityManager类对象，用于后续的crud操作
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.mdd.springbootdb.domain");
        entityManagerFactoryBean.setJpaProperties(buildHibernateProperties());
        //设置Jpa接口实现的厂商，用Hibernate
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {
            {
                setDatabase(Database.MYSQL);
            }
        });
        return entityManagerFactoryBean;
    }

    //    针对Hibernate的配置
    protected Properties buildHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        return hibernateProperties;
    }

    //  3.事务管理器
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    //  想看到Spring的异常体系，而不是底层Hibernate的异常体系
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    //  用于操作具体的sql
    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager());
    }
}
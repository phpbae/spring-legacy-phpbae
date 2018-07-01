package com.phpbae.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.phpbae.web.dao.repository")
public class JpaEntityManagerFactory {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabase(Database.MYSQL);
        adapter.setGenerateDdl(true); //스키마 초기화 여부

        Properties properties = new Properties();
        //properties.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.use_sql_comments", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(adapter);
        entityManagerFactoryBean.setJpaProperties(properties);
        entityManagerFactoryBean.setPackagesToScan("com.phpbae.web.business.domain");
        entityManagerFactoryBean.setDataSource(dataSource);

        return entityManagerFactoryBean;
    }

    /**
     * PlatformTransactionManager Interface
     * DataSourceTransactionManager / JpaTransactionManager / HibernateTransactionManager / JtaTransactionManager  -> 플랫폼트랜잭션매니저 인터페이스의 주요 구현 클래스
     * @param entityManagerFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}

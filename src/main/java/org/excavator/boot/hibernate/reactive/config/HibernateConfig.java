package org.excavator.boot.hibernaterecavator.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    LocalSessionFactoryBean localSessionFactoryBean(){
        var sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("org.excavator.boot.hibernaterecavator.entity");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());

        return sessionFactoryBean;
    }

    private Properties hibernateProperties() {
        var hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        return hibernateProperties;
    }

    @Bean
    public DataSource dataSource() {
        var dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://10.82.30.31:3306/hibernate_ractive");
        dataSource.setUser("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        var transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(localSessionFactoryBean().getObject());

        return transactionManager;
    }

}

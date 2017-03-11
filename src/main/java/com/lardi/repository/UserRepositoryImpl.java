package com.lardi.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@Configuration
public class UserRepositoryImpl {
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    @ConditionalOnProperty(name = "information.repository", havingValue = "database")
    public UserRepository bean3() {
        return new SeveInDataBaseUser(); }

    @Bean
    @ConditionalOnProperty(name = "information.repository", havingValue = "file")
    public UserRepository bean4() {
        return new SaveInFileUser();
    }
}

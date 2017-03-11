package com.lardi.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@Configuration
public class RecordRepositoryImpl  {
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    @ConditionalOnProperty(name = "information.repository", havingValue = "database")
    public RecordRepository bean1() {
        return new SeveInDataBaseRecords(); }

    @Bean
    @ConditionalOnProperty(name = "information.repository", havingValue = "file")
    public RecordRepository bean2() {
        return new SaveInFileRecords();
    }
}

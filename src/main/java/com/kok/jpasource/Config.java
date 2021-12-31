package com.kok.jpasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jay
 * @date 20 Dec 2021
 */
@Configuration
//for test
@EnableJpaRepositories(basePackages = "com.kok.jpasource.repo")
@EnableTransactionManagement
public class Config {

    @Bean
    public DataSource dataSource() {
        final DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("postgres");
        druidDataSource.setPassword("123456");
        druidDataSource.setUrl("jdbc:postgresql://119.27.160.235:5432/postgres");
        druidDataSource.setDriverClassName("org.postgresql.Driver");

        return druidDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.kok.jpasource.entity");

        final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(true);

        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        jpaProperties.put("hibernate.ddl-auto", "update");
        jpaProperties.put("hibernate.format_sql", "true");
        //Map<String, Object> hibernateProperties =
        //        new HibernateProperties()
        //                .determineHibernateProperties(jpaProperties, new HibernateSettings());
        //factory.setJpaPropertyMap(hibernateProperties);
        factory.setJpaPropertyMap(jpaProperties);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

//    @Bean
//    public UserRepository userRepository(LocalContainerEntityManagerFactoryBean factory) throws ClassNotFoundException {
//        final EntityManager nativeEntityManager = factory.createNativeEntityManager(null);
//
//        //拿到当前接口的父接口
//        final ParameterizedType parameterizedType = (ParameterizedType) UserRepository.class.getGenericInterfaces()[0];
//        //拿到第一个枚举
//        final Type type = parameterizedType.getActualTypeArguments()[0];
//        final Class<?> clazz = Class.forName(type.getTypeName());
//
//        final MyJpaRepo myJpaRepo = new MyJpaRepo(nativeEntityManager, clazz);
//
//        //spring做的事情
//        return (UserRepository) Proxy.newProxyInstance(
//                UserRepository.class.getClassLoader(),
//                new Class[]{UserRepository.class},
//                new MyJdkDynamicProxy(myJpaRepo));
//    }


}

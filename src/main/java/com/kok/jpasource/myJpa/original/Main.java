package com.kok.jpasource.myJpa.original;

import com.kok.jpasource.Config;
import com.kok.jpasource.repo.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * @author jay
 * @date 20 Dec 2021
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);

        final EntityManager nativeEntityManager = getEntityManager(annotationConfigApplicationContext);

        final Class<?> clazz = getT(UserRepository.class);

        final MyJpaRepo myJpaRepo = new MyJpaRepo(nativeEntityManager, clazz);

        //spring做的事情
        UserRepository userRepository = (UserRepository) Proxy.newProxyInstance(
                UserRepository.class.getClassLoader(),
                new Class[]{UserRepository.class},
                new MyJdkDynamicProxy(myJpaRepo));

        System.out.println(userRepository.findById(20L));
    }

    private static Class<?> getT(final Class clazz) throws ClassNotFoundException {
        //拿到当前接口的父接口
        final ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericInterfaces()[0];
        //拿到第一个枚举
        final Type type = parameterizedType.getActualTypeArguments()[0];
        return Class.forName(type.getTypeName());
    }

    private static EntityManager getEntityManager(final AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        final LocalContainerEntityManagerFactoryBean factory = annotationConfigApplicationContext.getBean(LocalContainerEntityManagerFactoryBean.class);
        return factory.createNativeEntityManager(null);
    }

}

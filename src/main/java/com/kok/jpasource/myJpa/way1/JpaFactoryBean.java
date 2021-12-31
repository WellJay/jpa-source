package com.kok.jpasource.myJpa.way1;

import com.kok.jpasource.myJpa.original.MyJdkDynamicProxy;
import com.kok.jpasource.myJpa.original.MyJpaRepo;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * @author jay
 * @date 05 Dec 2021
 */
//@Component  可以直接注入，但是只是固定的类UserRepository，想要动态注册只有通过后置处理器来动态注册
public class JpaFactoryBean implements FactoryBean {

    @Autowired
    private LocalContainerEntityManagerFactoryBean factory;

    private Class<?> repoInterface;

    public JpaFactoryBean(final Class<?> repoInterface) {
        this.repoInterface = repoInterface;
    }

    @Override
    public Object getObject() throws Exception {

        final EntityManager nativeEntityManager = factory.createNativeEntityManager(null);

        //拿到当前接口的父接口
        final ParameterizedType parameterizedType = (ParameterizedType) repoInterface.getGenericInterfaces()[0];
        //拿到第一个枚举
        final Type type = parameterizedType.getActualTypeArguments()[0];
        final Class<?> clazz = Class.forName(type.getTypeName());

        final MyJpaRepo myJpaRepo = new MyJpaRepo(nativeEntityManager, clazz);

        //spring做的事情
        return Proxy.newProxyInstance(
                repoInterface.getClassLoader(),
                new Class[]{repoInterface},
                new MyJdkDynamicProxy(myJpaRepo));
    }

    @Override
    public Class<?> getObjectType() {
        return repoInterface;
    }
}

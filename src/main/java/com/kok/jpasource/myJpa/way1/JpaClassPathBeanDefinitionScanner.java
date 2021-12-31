package com.kok.jpasource.myJpa.way1;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;

import java.util.Set;

/**
 * 自定义扫描器
 * 原有的不变，通过这个类动态注册
 *
 * @author jay
 * @date 05 Dec 2021
 */
public class JpaClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
    public JpaClassPathBeanDefinitionScanner(final BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected boolean isCandidateComponent(final AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(final String... basePackages) {
        final Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

        beanDefinitionHolders.forEach(b -> {
            final ScannedGenericBeanDefinition beanDefinition = (ScannedGenericBeanDefinition) b.getBeanDefinition();

            final String beanClassName = beanDefinition.getBeanClassName();

            //ConstructorResolver.class
            //convertedValue = converter.convertIfNecessary(originalValue, paramType, methodParam);
            //beanClassName后续Spring会推断出来对应的Class<?>
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);

            //偷天换日   mybatis     jpa是通过重新注册一个beanDefinition实现的
            beanDefinition.setBeanClass(JpaFactoryBean.class);
        });

        return beanDefinitionHolders;
    }
}

package com.kok.jpasource.myJpa.way2;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * 自定义扫描器
 * 原有的不变，通过这个类动态注册
 *
 * @author jay
 * @date 05 Dec 2021
 */
public class MyJPAPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public MyJPAPathBeanDefinitionScanner(final BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected boolean isCandidateComponent(final AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(final String... basePackages) {
        return super.doScan(basePackages);
    }
}

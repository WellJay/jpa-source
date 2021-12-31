package com.kok.jpasource.myJpa.way1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 动态注册 后置处理器
 *
 * @author jay
 * @date 05 Dec 2021
 */
@Component
public class JpaBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        final JpaClassPathBeanDefinitionScanner jpaClassPathBeanDefinitionScanner = new JpaClassPathBeanDefinitionScanner(beanDefinitionRegistry);

        // 限制必须实现Repository接口
        jpaClassPathBeanDefinitionScanner.addIncludeFilter(new AssignableTypeFilter(Repository.class));

        jpaClassPathBeanDefinitionScanner.scan("com.kok.jpasource.repo");
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }



}

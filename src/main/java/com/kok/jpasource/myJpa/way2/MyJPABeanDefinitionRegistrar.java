package com.kok.jpasource.myJpa.way2;

import com.kok.jpasource.myJpa.way1.JpaFactoryBean;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author jay
 * @date 29 Dec 2021
 */
public class MyJPABeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributesMap = annotationMetadata.getAnnotationAttributes(EnableMyJPA.class.getName());
        AnnotationAttributes annotationAttributes = Optional.ofNullable(AnnotationAttributes.fromMap(annotationAttributesMap)).orElseGet(AnnotationAttributes::new);
        // 获取需要扫描的包
        String[] packages = retrievePackagesName(annotationMetadata, annotationAttributes);

        // Spring提供的BeanDefinition扫描器，会自动过滤掉接口，应为接口没有实现类；
        //ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false, environment, resourceLoader);
        // 添加我们自定义注解的扫描
        //scanner.addIncludeFilter(new AnnotationTypeFilter(Repository.class));

        //custom class filters
        final MyJPAPathBeanDefinitionScanner myScanner = new MyJPAPathBeanDefinitionScanner(registry);

        final Set<BeanDefinitionHolder> beanDefinitionHolders = myScanner.doScan(packages);
        covertCandidateComponents(beanDefinitionHolders);
    }

    /**
     * 获取需要扫描的包
     */
    private String[] retrievePackagesName(AnnotationMetadata annotationMetadata, AnnotationAttributes annotationAttributes) {
        String[] packages = annotationAttributes.getStringArray("packages");
        if (packages.length > 0) {
            return packages;
        }
        String className = annotationMetadata.getClassName();
        int lastDot = className.lastIndexOf('.');
        return new String[]{className.substring(0, lastDot)};
    }

    /**
     * 注册 BeanDefinition
     */
    private void covertCandidateComponents(Set<BeanDefinitionHolder> beanDefinitionHolders) {
        beanDefinitionHolders.forEach(b -> {
            final ScannedGenericBeanDefinition beanDefinition = (ScannedGenericBeanDefinition) b.getBeanDefinition();

            final String beanClassName = beanDefinition.getBeanClassName();

            //ConstructorResolver.class
            //convertedValue = converter.convertIfNecessary(originalValue, paramType, methodParam);
            //beanClassName后续Spring会推断出来对应的Class<?>
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);

            //偷天换日   mybatis jpa
            beanDefinition.setBeanClass(JpaFactoryBean.class);
        });
    }
}

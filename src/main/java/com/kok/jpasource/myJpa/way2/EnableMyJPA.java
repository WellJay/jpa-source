package com.kok.jpasource.myJpa.way2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jay
 * @date 29 Dec 2021
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyJPABeanDefinitionRegistrar.class)
public @interface EnableMyJPA {

    String[] packages() default {};

}

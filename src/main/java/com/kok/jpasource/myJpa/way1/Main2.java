package com.kok.jpasource.myJpa.way1;

import com.kok.jpasource.repo.TestRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 动态注册给Spring
 *
 * @author jay
 * @date 20 Dec 2021
 */
public class Main2 {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(Config2.class);

        final TestRepository testRepository = ioc.getBean(TestRepository.class);

        System.out.println(testRepository.findById(20L));
    }

}

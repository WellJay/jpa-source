package com.kok.jpasource.myJpa.original;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jay
 * @date 20 Dec 2021
 */
public class MyJdkDynamicProxy implements InvocationHandler {

    MyJpaRepo myJpaRepo;

    //构造进真实调用对象
    public MyJdkDynamicProxy(final MyJpaRepo myJpaRepo) {
        this.myJpaRepo = myJpaRepo;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return myJpaRepo.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(myJpaRepo, args);
    }
}

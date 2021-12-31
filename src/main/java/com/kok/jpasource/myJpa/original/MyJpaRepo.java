package com.kok.jpasource.myJpa.original;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * @author jay
 * @date 20 Dec 2021
 */
public class MyJpaRepo implements CrudRepository {

    EntityManager entityManager;
    Class clazz;

    public MyJpaRepo(final EntityManager entityManager, final Class clazz) {
        this.entityManager = entityManager;
        this.clazz = clazz;
    }

    @Override
    public Object save(final Object entity) {
        return null;
    }

    @Override
    public Iterable saveAll(final Iterable entities) {
        return null;
    }

    @Override
    public Optional findById(final Object o) {
        System.out.println("---my customer repository---findById");
        return Optional.of(entityManager.find(clazz, o));
    }

    @Override
    public boolean existsById(final Object o) {
        return false;
    }

    @Override
    public Iterable findAll() {
        return null;
    }

    @Override
    public Iterable findAllById(final Iterable iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final Object o) {

    }

    @Override
    public void delete(final Object entity) {

    }

    @Override
    public void deleteAllById(final Iterable iterable) {

    }

    @Override
    public void deleteAll(final Iterable entities) {

    }

    @Override
    public void deleteAll() {

    }
}

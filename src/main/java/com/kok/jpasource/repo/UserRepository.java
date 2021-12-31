package com.kok.jpasource.repo;

import com.kok.jpasource.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author jay
 * @date 20 Dec 2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String tester);

}
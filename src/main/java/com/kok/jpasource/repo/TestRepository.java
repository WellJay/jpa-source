package com.kok.jpasource.repo;

import com.kok.jpasource.UserDto;
import com.kok.jpasource.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author jay
 * @date 20 Dec 2021
 */
@Component
public interface TestRepository extends JpaRepository<User, Long> {

    UserDto findByName(String tester);

}
package com.kok.jpasource;

import com.kok.jpasource.entity.User;
import com.kok.jpasource.repo.TestRepository;
import com.kok.jpasource.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@ContextConfiguration(classes = Config.class)
@SpringBootTest
class JpaSourceApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestRepository testRepository;

    @Test
    void contextLoads() {
        final Optional<User> user = userRepository.findById(1L);
        System.out.println(1);
    }

}

package cz.kiv.pia.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JdbcTest
@TestPropertySource(properties = {
        // do not use embedded database
        "spring.test.database.replace=none",
        // run tests against real MySQL database running in Docker using Testcontainers
        "spring.datasource.url=jdbc:tc:mysql:8:///bikesharing",
})
@Sql
public class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByEmail(){
        var result = userRepository.getUserByEmail("pepik@seznam.cz");

        assertEquals("1", result.getId());
    }

    @Test
    void saveUser(){
        var result = userRepository.save("svobik", "svoboda@seznam.cz",
                "$2y$10$EydBs9tESc9gJeeJa7ZKeOKg9ZYSK3MY0nyiOYrW4beZ4uNfkslES", "SERVICEMAN");

        var user = userRepository.getUserByEmail("svoboda@seznam.cz");
        assertNotNull(user);
    }

    // nested @Configuration is loaded into Spring application context by default
    @TestConfiguration
    // scan for @Repository beans within given base package
    @ComponentScan(basePackages = "cz.kiv.pia.jdbc.repository", includeFilters = @ComponentScan.Filter(classes = Repository.class))
    static class ITConfiguration {
    }
}

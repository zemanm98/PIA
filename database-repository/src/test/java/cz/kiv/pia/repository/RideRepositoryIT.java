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

@JdbcTest
@TestPropertySource(properties = {
        // do not use embedded database
        "spring.test.database.replace=none",
        // run tests against real MySQL database running in Docker using Testcontainers
        "spring.datasource.url=jdbc:tc:mysql:8:///bikesharing",
})
@Sql
public class RideRepositoryIT {

    @Autowired
    private RideRepository rideRepository;

    @Test
    void getAllUserRides(){
        var result = rideRepository.getAllUserRides("1");
        assertEquals(1, result.size());
    }

    @Test
    void createRide(){
        var result = rideRepository.createRide("1", 1, 1, LocalDateTime.now(), "STARTED");
        assertEquals(2, result);
    }

    @Test
    void endRide(){
        var result = rideRepository.endRide(2, 2, LocalDateTime.now(), "COMPLETED");
        assertEquals(0, result);
    }

    // nested @Configuration is loaded into Spring application context by default
    @TestConfiguration
    // scan for @Repository beans within given base package
    @ComponentScan(basePackages = "cz.kiv.pia.jdbc.repository", includeFilters = @ComponentScan.Filter(classes = Repository.class))
    static class ITConfiguration {
    }
}

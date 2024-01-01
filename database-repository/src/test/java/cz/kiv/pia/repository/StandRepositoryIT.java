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
public class StandRepositoryIT {
    @Autowired
    private StandRepository standRepository;

    @Test
    void getAll(){
        var result = standRepository.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void getSpecificStand(){
        var result = standRepository.getStandById(1);
        assertEquals("náměstí Republiky", result.getName());
    }

    // nested @Configuration is loaded into Spring application context by default
    @TestConfiguration
    // scan for @Repository beans within given base package
    @ComponentScan(basePackages = "cz.kiv.pia.jdbc.repository", includeFilters = @ComponentScan.Filter(classes = Repository.class))
    static class ITConfiguration {
    }
}

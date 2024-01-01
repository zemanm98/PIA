package cz.kiv.pia.repository;

import cz.kiv.pia.domain.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@JdbcTest
@TestPropertySource(properties = {
        // do not use embedded database
        "spring.test.database.replace=none",
        // run tests against real MySQL database running in Docker using Testcontainers
        "spring.datasource.url=jdbc:tc:mysql:8:///bikesharing",
})
@Sql
public class BikeRepositoryIT {
    @Autowired
    private BikeRepository bikeRepository;

    @Test
    void getAll(){
        var result = bikeRepository.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void getBikesByStand(){
        var result = bikeRepository.getBikesByStandId(1);
        assertEquals(1, result.size());
    }

    @Test
    void getBikeById(){
        var result = bikeRepository.getBikeById(1);
        assertEquals(1, result.getId());
    }

    @Test
    void bikeInUse(){
        var result = bikeRepository.setBikeInUse(2, 1);
        var bikes = bikeRepository.getBikesByStandId(2);
        assertEquals(0, bikes.size());
    }

    @Test
    void setStand(){
        var result = bikeRepository.updateBikeStand(2, 1, 0);
        var bike = bikeRepository.getBikeById(2);
        assertEquals(1, bike.getStand().getId());
    }

    @Test
    void repairBike(){
        LocalDate today = LocalDate.now();
        var result = bikeRepository.repairBikeWithId(1, today);
        var bike = bikeRepository.getBikeById(1);
        assertFalse(bike.isDueForService(Period.ofMonths(6)));
    }

    @Test
    void updateBikeLocation(){
        Location newLocation = new Location("13.3786114E", "49.7479433N");

        var result = bikeRepository.updateBike(2, newLocation);
        var bike = bikeRepository.getBikeById(2);

        assertEquals(newLocation, bike.getLocation());
    }
    // nested @Configuration is loaded into Spring application context by default
    @TestConfiguration
    // scan for @Repository beans within given base package
    @ComponentScan(basePackages = "cz.kiv.pia.jdbc.repository", includeFilters = @ComponentScan.Filter(classes = Repository.class))
    static class ITConfiguration {
    }
}

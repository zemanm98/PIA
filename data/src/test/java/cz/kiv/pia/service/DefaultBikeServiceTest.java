package cz.kiv.pia.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.domain.Location;
import cz.kiv.pia.domain.Stand;
import cz.kiv.pia.repository.BikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DefaultBikeServiceTest {

    @Mock
    private BikeRepository bikeRepository;

    private BikeService bikeService;

    @BeforeEach
    void setUp(){
        this.bikeService = new DefaultBikeService(bikeRepository, Period.ofMonths(6));
    }

    @Test
    void rideAbleBikes(){
        var bike1 = new Bike(0, null, LocalDate.of(2023, Month.JANUARY, 10), null);
        var bike2 = new Bike(1, null, LocalDate.of(2023, Month.JANUARY, 10), null);
        var bike3 = new Bike(2, null, LocalDate.of(2023, Month.NOVEMBER, 10), null);

        when(bikeRepository.getAll()).thenReturn(List.of(bike1, bike2, bike3));

        var result = bikeService.getAllRideableBikes();

        verify(bikeRepository).getAll();

        assertTrue(result.contains(bike3));
        assertFalse(result.contains(bike1));
        assertFalse(result.contains(bike2));
    }

    @Test
    void bikesByStandId(){
        var bike1 = new Bike(0, null, LocalDate.of(2023, Month.JANUARY, 10),
                new Stand(1, "namesti Republiky", new Location("49.7479433N", "13.3786114E")));
        var bike2 = new Bike(1, null, LocalDate.of(2023, Month.JANUARY, 10),
                new Stand(2, "FAV", new Location("49.7879433N", "13.3986114E")));
        var bike3 = new Bike(2, null, LocalDate.of(2023, Month.NOVEMBER, 10),
                new Stand(2, "FAV", new Location("49.7879433N", "13.3986114E")));

        when(bikeRepository.getBikesByStandId(2)).thenReturn(List.of(bike2, bike3));

        var result = bikeService.getBikesByStandId(2);

        verify(bikeRepository).getBikesByStandId(2);

        assertTrue(result.contains(bike3));
        assertFalse(result.contains(bike2));
    }

    @Test
    void bikesToRepair(){
        var bike1 = new Bike(0, null, LocalDate.of(2023, Month.JANUARY, 10), null);
        var bike2 = new Bike(1, null, LocalDate.of(2023, Month.JANUARY, 10), null);
        var bike3 = new Bike(2, null, LocalDate.of(2023, Month.NOVEMBER, 10), null);

        when(bikeRepository.getAll()).thenReturn(List.of(bike1, bike2, bike3));

        var result = bikeService.getAllBikesToRepair();

        verify(bikeRepository).getAll();

        assertTrue(result.contains(bike1));
        assertTrue(result.contains(bike2));
        assertFalse(result.contains(bike3));
    }
}

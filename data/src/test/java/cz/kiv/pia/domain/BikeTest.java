package cz.kiv.pia.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BikeTest {
    private static final Period serviceInterval = Period.ofMonths(6);

    @Nested
    class isDueForService{
        @Test
        void yes(){
            var bike = new Bike(0, null, LocalDate.of(2023, Month.JANUARY, 10), null);

            var result = bike.isDueForService(serviceInterval);

            assertTrue(result);
        }

        @Test
        void no(){
            var bike = new Bike(0, null, LocalDate.of(2023, Month.NOVEMBER, 10), null);

            var result = bike.isDueForService(serviceInterval);

            assertFalse(result);
        }
        @Test
        void serviced(){
            var bike = new Bike(0, null, LocalDate.of(2023, Month.JANUARY, 10), null);
            bike.markServiced();
            var result = bike.isDueForService(serviceInterval);

            assertFalse(result);
        }
    }

    @Test
    void addToStand() {
        var stand = new Stand(0, "náměstí Republiky", new Location("49.7479433N", "13.3786114E"));
        var bike = new Bike(0, new Location("49.7479433N", "13.3786114E"), LocalDate.now(), null);

        bike.addToStand(stand);

        assertEquals(stand, bike.getStand());
        assertTrue(stand.getBikes().contains(bike));
    }

    @Test
    void removeFromStand() {
        var stand = new Stand(0, "náměstí Republiky", new Location("49.7479433N", "13.3786114E"));
        var bike = new Bike(0, new Location("49.7479433N", "13.3786114E"), LocalDate.now(), stand);

        bike.removeFromStand();

        assertNull(bike.getStand());
        assertFalse(stand.getBikes().contains(bike));
    }
}

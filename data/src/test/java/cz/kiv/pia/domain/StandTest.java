package cz.kiv.pia.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StandTest {

    @Test
    void addBike(){
        var stand = new Stand(0, "náměstí Republiky", new Location("49.7479433N", "13.3786114E"));
        var bike = new Bike(0, new Location("49.7479433N", "13.3786114E"), LocalDate.now(), null);

        stand.addBike(bike);

        assertEquals(1, stand.getBikes().size());
        assertTrue(stand.getBikes().contains(bike));
    }

    @Test
    void removeBike(){
        var stand = new Stand(0, "náměstí Republiky", new Location("49.7479433N", "13.3786114E"));
        var bike = new Bike(0, new Location("49.7479433N", "13.3786114E"), LocalDate.now(), null);

        stand.addBike(bike);
        stand.removeBike(bike);

        assertEquals(0, stand.getBikes().size());
        assertFalse(stand.getBikes().contains(bike));
    }
}

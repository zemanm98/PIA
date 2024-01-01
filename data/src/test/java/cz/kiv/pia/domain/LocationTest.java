package cz.kiv.pia.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    @Test
    void parseLocation(){
        var location = new Location("49.7479433N", "13.3786114E");

        assertEquals(49.7479433, location.getLongitude());
        assertEquals(13.3786114, location.getLatitude());
    }

}

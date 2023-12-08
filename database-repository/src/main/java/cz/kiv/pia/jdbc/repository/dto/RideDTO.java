package cz.kiv.pia.jdbc.repository.dto;

import java.time.LocalDateTime;

public record RideDTO(
        int id,

        UserDTO user,

        BikeDTO bike,

        StandDTO startStand,

        StandDTO endStand,

        LocalDateTime startTimestamp,

        LocalDateTime endTimestamp,

        int state
) { }

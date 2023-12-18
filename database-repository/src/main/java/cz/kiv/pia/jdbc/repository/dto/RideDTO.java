package cz.kiv.pia.jdbc.repository.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Rides. Object is mapped to Ride when retrieved from database.
 * @param id - id of this ride
 * @param user - rides user
 * @param bike - rides bike
 * @param startStand - rides start stand
 * @param endStand - rides end stand
 * @param startTimestamp - rides start timestamp
 * @param endTimestamp - rides end time stamp
 * @param state - rides state: STARTED or COMPLETED
 */
public record RideDTO(
        int id,

        String user,

        BikeDTO bike,

        StandDTO startStand,

        StandDTO endStand,

        LocalDateTime startTimestamp,

        LocalDateTime endTimestamp,

        String state
) { }

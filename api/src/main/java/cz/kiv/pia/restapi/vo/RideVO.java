package cz.kiv.pia.restapi.vo;

import cz.kiv.pia.domain.Ride;

import java.time.LocalDateTime;

/**
 * Value object of a ride for presentation layer.
 * @param bike - bike of a ride
 * @param startStand - starting stand of a ride
 * @param endStand - ending stand of a ride
 * @param startTimeStamp - start time stamp of a ride
 * @param endTimeStamp - end time stamp of a ride
 * @param state - state of a ride. COMPLETED or STARTED
 */
public record RideVO(
        BikeVO bike,

        StandVO startStand,

        StandVO endStand,

        LocalDateTime startTimeStamp,

        LocalDateTime endTimeStamp,

        String state
) {
    public RideVO(Ride ride){
        this(new BikeVO(ride.getBike()), new StandVO(ride.getStartStand()), new StandVO(ride.getEndStand()),
                ride.getStartTimestamp(), ride.getEndTimestamp(), ride.getState().toString());
    }
}

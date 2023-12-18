package cz.kiv.pia.repository;

import cz.kiv.pia.domain.Ride;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Repository interface for communicating with a database engine specifically ride table.
 */
public interface RideRepository {
    /**
     * Returns all Rides ridden by user with given user id.
     * @param userId - id of a user
     * @return all rides of this user
     */
    Collection<Ride> getAllUserRides(String userId);

    /**
     * Creates a new ride
     * @param userId - user id
     * @param bikeId - bike id
     * @param startStandId - start stand id
     * @param startTimestamp - start time stamp
     * @param state - state (STARTED)
     * @return - success indicator.
     */
    int createRide(String userId, int bikeId, int startStandId, LocalDateTime startTimestamp, String state);

    /**
     * Completes STARRTED ride
     * @param rideId - id of STARTED ride
     * @param standId - end stand id
     * @param endTimestamp - end timestamp
     * @param state - end state (COMPLETED)
     * @return - success indicator
     */
    int endRide(int rideId, int standId, LocalDateTime endTimestamp, String state);

}

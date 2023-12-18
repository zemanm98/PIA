package cz.kiv.pia.service;

import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.domain.Ride;

import java.util.Collection;
/**
 * Service interface used by controllers for Ride Objects
 */
public interface RideService {
    /**
     * Get all Rides ridden by specific user with given id
     * @param userId - user id
     * @return - rides of user with given id
     */
    Collection<Ride> getAllUserRides(String userId);

    /**
     * Creates STARTED ride with given user and bike
     * @param userId - user id
     * @param bike - bike
     * @return - success indicator
     */
    int createRide(String userId, Bike bike);

    /**
     * Completes a STARTED ride.
     * @param rideId - ride id
     * @param standId - end stand id
     * @return - success indicator
     */
    int endRide(int rideId, int standId);
}

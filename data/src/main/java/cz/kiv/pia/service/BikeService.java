package cz.kiv.pia.service;

import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.domain.Location;

import java.util.Collection;

/**
 * Service interface used by controllers for Bike Objects
 */
public interface BikeService {
    /**
     * Get all bikes that are currently rideable.
     * @return - all rideable bikes
     */
    Collection<Bike> getAllRideableBikes();

    /**
     * Get all bikes of certain stand.
     * @param id -stand id
     * @return - bikes of a certain stand
     */
    Collection<Bike> getBikesByStandId(int id);

    /**
     * Returns all bikes that are due for repair
     * @return - bike to repair
     */
    Collection<Bike> getAllBikesToRepair();

    /**
     * repairs one specific bike with given id
     * @param id - bike id
     * @return - success indicator
     */
    int RepairBikeWithId(int id);
    /**
     * Returns one specific bike with given id
     * @param id - bike id
     * @return - bike with given id
     */
    Bike getBikeById(int id);

    /**
     * Updates location of bike with given id
     * @param id - bike id
     * @param newLocation - new location
     * @return - success indicator.
     */
    int updateBike(int id, Location newLocation);

    /**
     * Sets bike to be in used
     * @param id - bike id
     * @return - success indicator
     */
    int setBikeInUse(int id);

    /**
     * Updates bikes stand.
     * @param bikeId - bike id
     * @param standId - new stand id
     * @return - success indicator
     */
    int updateBikeStand(int bikeId, int standId);
}

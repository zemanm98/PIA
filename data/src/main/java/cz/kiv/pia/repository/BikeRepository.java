package cz.kiv.pia.repository;

import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.domain.Location;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Repository interface for communicating with a database engine specifically bike table.
 */
public interface BikeRepository {
    /**
     * Get All bikes in system
     * @return - all bikes
     */
    Collection<Bike> getAll();

    /**
     * Get all bikes with given stand id. That are not in use.
     * @param id - id of s tand
     * @return - return bikes with stand id = id
     */
    Collection<Bike> getBikesByStandId(int id);

    /**
     * Updates time stamp of a bike with given id
     * @param id - id of a bike
     * @param timeStamp - new last service timestamp
     * @return - success indicator.
     */
    int repairBikeWithId(int id, LocalDate timeStamp);

    /**
     * Get one bike by given id
     * @param id - id of a bike
     * @return - one bike
     */
    Bike getBikeById(int id);

    /**
     * Updates location of a bike with given id
     * @param id - id of a bike
     * @param location - new location of a bike.
     * @return - succes indicator.
     */
    int updateBike(int id, Location location);

    /**
     * Sets certain bike as inUse.
     * @param id - id of a bike
     * @param inUse - 1 for marking as in use.
     * @return - succes indicator.
     */
    int setBikeInUse(int id, int inUse);

    /**
     * Updated bike new stand by given stand id and marks it as not in use.
     * @param bikeId - bike ID
     * @param standId - ID of a new stand
     * @param inUse - 0 for marking as not in use
     * @return - succes indicator.
     */
    int updateBikeStand(int bikeId, int standId, int inUse);
}

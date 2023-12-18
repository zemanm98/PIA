package cz.kiv.pia.service;

import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.domain.Ride;
import cz.kiv.pia.repository.RideRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;

import static org.slf4j.LoggerFactory.getLogger;
/**
 * Service used for manipulating with Rides in this system. Does necessary operations bound to Ride Objects
 */
@Transactional
@Service
public class DefaultRideService implements RideService{
    private static final Logger LOG = getLogger(DefaultBikeService.class);
    /**
     * repository interface for database communication
     */
    private final RideRepository rideRepository;

    public DefaultRideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    /**
     * Returns all Rides ridden by user with given id
     * @param userId - user id
     * @return - all user rides.
     */
    @Override
    public Collection<Ride> getAllUserRides(String userId) {
        LOG.info("retrieving all rides of User " + userId);

        return rideRepository.getAllUserRides(userId).stream().sorted(new Comparator<Ride>() {
            @Override
            public int compare(Ride o1, Ride o2) {
                return -o1.getStartTimestamp().compareTo(o2.getStartTimestamp());
            }
        }).toList();
    }

    /**
     * Creates a new ride with given user and bike
     * @param userId - user id
     * @param bike - bike
     * @return - success indicator
     */
    @Override
    public int createRide(String userId, Bike bike) {
        LOG.info("Starting ride with bike " + bike.getId() + " and user " + userId);
        return rideRepository.createRide(userId, bike.getId(), bike.getStand().getId(), LocalDateTime.now(), "STARTED");
    }

    /**
     * Ends a certain ride with given id and completes with given stand id
     * @param rideId - ride id
     * @param standId - end stand id
     * @return - success indicator
     */
    @Override
    public int endRide(int rideId, int standId) {
        LOG.info("Ending ride " + rideId + " ending stand " + standId);
        return rideRepository.endRide(rideId, standId, LocalDateTime.now(), "COMPLETED");
    }
}

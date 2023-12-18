package cz.kiv.pia.service;

import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.domain.Location;
import cz.kiv.pia.repository.BikeRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Service used for manipulating with Bikes in this system. Does necessary operations bound to Bike Objects
 */
@Transactional
@Service
public class DefaultBikeService implements BikeService{
    private static final Logger LOG = getLogger(DefaultBikeService.class);
    /**
     * repository interface for database communication
     */
    private final BikeRepository bikeRepository;
    /**
     * repair period of bikes
     */
    private final Period serviceInterval;

    public DefaultBikeService (BikeRepository bikeRepository, Period serviceInterval) {
        this.bikeRepository = bikeRepository;
        this.serviceInterval = serviceInterval;
    }

    /**
     * Get all bikes that are rideable in this system
     * @return - all rideable bikes
     */
    @Override
    public Collection<Bike> getAllRideableBikes() {
        LOG.info("Getting all rideable bikes");
        Predicate<Bike> streamsPredicate = item -> !item.isDueForService(this.serviceInterval);
        return this.bikeRepository.getAll().stream().filter(streamsPredicate).collect(Collectors.toList());
    }

    /**
     * Get all bikes of a certain stand that are not in use
     * @param id - stand id
     * @return - all bikes belonging to stand with given id.
     */
    @Override
    public Collection<Bike> getBikesByStandId(int id) {
        LOG.info("Getting all bikes of specified Stand");
        Predicate<Bike> streamsPredicate = item -> !item.isDueForService(this.serviceInterval);

        return this.bikeRepository.getBikesByStandId(id).stream().filter(streamsPredicate).collect(Collectors.toList());
    }

    /**
     * Returns all bikes that are due for a repair. Filters with Predicate and Comparator.
     * @return - all bikes that are due for a repair.
     */
    @Override
    public Collection<Bike> getAllBikesToRepair() {
        LOG.info("Getting all bikes that needs to be repaired");
        Predicate<Bike> streamsPredicate = item -> item.isDueForService(this.serviceInterval);
        return bikeRepository.getAll().stream().filter(streamsPredicate).sorted(new Comparator<Bike>() {
            @Override
            public int compare(Bike o1, Bike o2) {
                return o1.getLastServiceDate().compareTo(o2.getLastServiceDate());
            }
        }).toList();
    }

    /**
     * Calls repository for a repair of bike with givewn id.
     * @param id - bike id
     * @return - succes indicator
     */
    @Override
    public int RepairBikeWithId(int id) {
        LOG.info("Repairing bike with  Id " + id);
        return this.bikeRepository.repairBikeWithId(id, LocalDate.now());
    }

    /**
     * Returns bike with given id
     * @param id - bike id
     * @return - bike with given id
     */
    @Override
    public Bike getBikeById(int id) {
        return bikeRepository.getBikeById(id);
    }

    /**
     * Updates location of a bike with given id
     * @param id - bike id
     * @param newLocation - new location
     * @return - success indicator
     */
    @Override
    public int updateBike(int id, Location newLocation) {
        return bikeRepository.updateBike(id, newLocation);
    }

    /**
     * Sets bike as in use. During process of starting a ride.
     * @param id - bike id
     * @return - success indicator
     */
    @Override
    public int setBikeInUse(int id) {
        return bikeRepository.setBikeInUse(id, 1);
    }

    /**
     * Updates bikes stand with given stand id
     * @param bikeId - bike id
     * @param standId - new stand id
     * @return - success indicator
     */
    @Override
    public int updateBikeStand(int bikeId, int standId) {
        return bikeRepository.updateBikeStand(bikeId, standId, 0);
    }


}

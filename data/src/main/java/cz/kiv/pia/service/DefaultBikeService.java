package cz.kiv.pia.service;

import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.repository.BikeRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

@Transactional
@Service
public class DefaultBikeService implements BikeService{

    private static final Logger LOG = getLogger(DefaultBikeService.class);

    private final BikeRepository bikeRepository;

    public DefaultBikeService (BikeRepository bikeRepository) {this.bikeRepository = bikeRepository;}

    @Override
    public Collection<Bike> getAll() {
        LOG.info("Getting all bikes");

        return this.bikeRepository.getAll();
    }
}

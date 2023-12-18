package cz.kiv.pia.service;

import cz.kiv.pia.domain.Stand;
import cz.kiv.pia.repository.StandRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;
/**
 * Service used for manipulating with Stands in this system. Does necessary operations bound to Stand Objects
 */
@Transactional
@Service
public class DefaultStandService implements StandService {
    private static final Logger LOG = getLogger(DefaultStandService.class);
    /**
     * repository interface for database communication
     */
    private final StandRepository standRepository;

    public DefaultStandService(StandRepository standRepository) {
        this.standRepository = standRepository;
    }

    /**
     * Returns all stands in system
     * @return - all stands
     */
    @Override
    public Collection<Stand> getAll() {
        LOG.info("Getting all stands");

        return standRepository.getAll();
    }

    /**
     * Returns one specific stand with given id
     * @param id - stand id
     * @return - one specific stand
     */
    @Override
    public Stand getStandById(int id) {
        return standRepository.getStandById(id);
    }

}

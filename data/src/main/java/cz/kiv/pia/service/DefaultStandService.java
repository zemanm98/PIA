package cz.kiv.pia.service;

import cz.kiv.pia.domain.Stand;
import cz.kiv.pia.repository.StandRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

@Transactional
@Service
public class DefaultStandService implements StandService {
    private static final Logger LOG = getLogger(DefaultStandService.class);

    private final StandRepository standRepository;

    public DefaultStandService(StandRepository standRepository) {
        this.standRepository = standRepository;
    }

    @Override
    public Collection<Stand> getAll() {
        LOG.info("Getting all stands");

        return standRepository.getAll();
    }

    @Override
    public Stand getStand(int id) {
        LOG.info("Getting certain stand by its id");
        return standRepository.getStandById(id);
    }
}

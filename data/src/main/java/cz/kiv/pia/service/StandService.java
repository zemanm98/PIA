package cz.kiv.pia.service;

import cz.kiv.pia.domain.Stand;

import java.util.Collection;

/**
 * Service for {@link Stand} management.
 */
public interface StandService {
    /**
     * Retrieves all {@link Stand}s currently in the system.
     *
     * @return All stands
     */
    Collection<Stand> getAll();

    /**
     * Returns certain stand with given id.
     * @param id - stand id
     * @return - stan with given id
     */
    Stand getStandById(int id);

}

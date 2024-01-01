package cz.kiv.pia.repository;

import cz.kiv.pia.domain.Stand;

import java.util.Collection;

/**
 * Repository storing all information related to bike stands.
 */
public interface StandRepository {
    /**
     * Retrieves all stands currently in the system.
     *
     * @return All stands
     */
    Collection<Stand> getAll();
    /**
     * Gets pne specific stand by given stand id.
     * @param id - id of a stand
     * @return - found Stand with given id.
     */
    Stand getStandById(int id);

}

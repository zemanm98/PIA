package cz.kiv.pia.service;

import cz.kiv.pia.domain.User;
/**
 * Service interface used by controllers for User Objects
 */
public interface UserService {
    /**
     * Saves new user
     * @param userName - users name
     * @param email - user email
     * @param password - user password
     * @return - success indicator
     */
    int save(String userName, String email, String password);

    /**
     * Returns user with given email.
     * @param email - email of a user
     * @return - Found user or null
     */
    User loadUserByUsername(String email);

}

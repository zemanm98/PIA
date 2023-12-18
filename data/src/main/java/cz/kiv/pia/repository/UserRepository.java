package cz.kiv.pia.repository;

import cz.kiv.pia.domain.User;
/**
 * Repository interface for communicating with a database engine specifically user table.
 */
public interface UserRepository {
    /**
     * Saving a new user in to database
     * @param userName - user name of user
     * @param email - email of user
     * @param password - password of user
     * @param role - role of user
     * @return - success indicator
     */
    int save(String userName, String email, String password, String role);

    /**
     * Returns one user by given unique email
     * @param email - email of a suer
     * @return - User with given email
     */
    User getUserByEmail(String email);
}

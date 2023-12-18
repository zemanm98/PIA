package cz.kiv.pia.service;

import cz.kiv.pia.domain.User;
import cz.kiv.pia.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.slf4j.LoggerFactory.getLogger;
/**
 * Service used for manipulating with Users in this system. Does necessary operations bound to User Objects
 */
@Transactional
@Service
public class DefaultUserService implements UserService{

    private static final Logger LOG = getLogger(DefaultUserService.class);
    /**
     * repository interface for database communication
     */
    private final UserRepository userRepository;


    public DefaultUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Saves a new user
     * @param userName - users name
     * @param email - user email
     * @param password - user password
     * @return - success indicator
     */
    @Override
    public int save(String userName, String email, String password) {
        LOG.info("Creating a new user with email " + email);
        return userRepository.save(userName, email, password, "REGULAR");
    }

    /**
     * Loads one user by given email
     * @param email - email of a user
     * @return - User with given email or null
     */
    @Override
    public User loadUserByUsername(String email){
        LOG.info("Retrieving user with email " + email);
        return userRepository.getUserByEmail(email);
    }
}

package cz.kiv.pia.jdbc.repository.dto;

/**
 * Data Transfer Object for Users. Object is mapped to User when retrieved from database.
 * @param id - id of user.
 * @param userName - user name of user.
 * @param email - email of user.
 * @param password - password of user.
 * @param role - role of this user. Either REGULAR or SERVICEMAN.
 */
public record UserDTO(
        String id,

        String userName,

        String email,

        String password,

        String role
) { }

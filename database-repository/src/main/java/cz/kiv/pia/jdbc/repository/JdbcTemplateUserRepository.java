package cz.kiv.pia.jdbc.repository;

import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.domain.Location;
import cz.kiv.pia.domain.Stand;
import cz.kiv.pia.domain.User;
import cz.kiv.pia.jdbc.repository.dto.UserDTO;
import cz.kiv.pia.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
/**
 * Jdbc template engine used for communication with MySQL database. Specifically with user table.
 */
@Primary
@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<UserDTO> rowMapper;

    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = userRowMapper;
    }

    /**
     * Inserts new user into user table.
     * @param userName - user name of user
     * @param email - email of user
     * @param password - password of user
     * @param role - role of user
     * @return - success indicator
     */
    @Override
    public int save(String userName, String email, String password, String role) {
        var sql = """
                INSERT INTO user (userName, email, role, password) VALUES (?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql, userName, email, role, password);
    }

    /**
     * Returns User with given email
     * @param email - email of a suer
     * @return - User found with this email
     */
    @Override
    public User getUserByEmail(String email) {
        var sql = """
                SELECT user.id, user.userName, user.email, user.role, user.password FROM user WHERE user.email = ?
                """;
        var result = jdbcTemplate.query(sql, rowMapper, email);
        var returnList = result.stream()
                .map(userDTO -> {
                    var id = userDTO.id();
                    var dtoUserName = userDTO.userName();
                    var role = User.Role.valueOf(userDTO.role());
                    var userEmail = userDTO.email();
                    var password = userDTO.password();
                    return new User(id, dtoUserName, userEmail, role, password);
                })
                .toList();
        if(returnList.size() > 0){
            return returnList.get(0);
        }
        return null;
    }
}

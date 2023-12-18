package cz.kiv.pia.jdbc.repository;

import cz.kiv.pia.jdbc.repository.dto.UserDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Class used for mapping data from database to UserDTO objects that are mapped later to User objects.
 * Take values by String key used in SQL query and retrieves data from ResultSet.
 */
@Service
public class UserRowMapper implements RowMapper<UserDTO> {
    @Override
    public UserDTO mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getString("id");
        var userName = rs.getString("userName");
        var password = rs.getString("password");
        var email = rs.getString("email");
        var role = rs.getString("role");
        return new UserDTO(id, userName, email, password, role);
    }
}

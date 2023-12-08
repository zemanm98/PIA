package cz.kiv.pia.jdbc.repository;

import cz.kiv.pia.jdbc.repository.dto.StandDTO;
import cz.kiv.pia.jdbc.repository.dto.LocationDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link RowMapper} mapping database rows into {@link StandDTO} instances.
 */
@Service
public class StandRowMapper implements RowMapper<StandDTO> {
    @Override
    public StandDTO mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getString("id");
        var name = rs.getString("name");
        var longitude = rs.getString("longitude");
        var latitude = rs.getString("latitude");
        return new StandDTO(Integer.parseInt(id), name, new LocationDTO(longitude, latitude));
    }
}
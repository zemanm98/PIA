package cz.kiv.pia.jdbc.repository;

import cz.kiv.pia.jdbc.repository.dto.BikeDTO;
import cz.kiv.pia.jdbc.repository.dto.LocationDTO;
import cz.kiv.pia.jdbc.repository.dto.StandDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class used for mapping data from database to BikeDTO objects that are mapped later to Bike objects.
 * Take values by String key used in SQL query and retrieves data from ResultSet.
 */
@Service
public class BikeRowMapper implements RowMapper<BikeDTO> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public BikeDTO mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getString("bike.id");
        var longitude = rs.getString("bike.longitude");
        var latitude = rs.getString("bike.latitude");
        var lastServiceDate = rs.getString("bike.lastServiceDate");
        var standId = rs.getString("stand.id");
        var standLongitude = rs.getString("stand.longitude");
        var standLatitude = rs.getString("stand.latitude");
        var standName = rs.getString("stand.name");
        return new BikeDTO(Integer.parseInt(id), new LocationDTO(longitude, latitude), LocalDate.parse(lastServiceDate, formatter),
                new StandDTO(Integer.parseInt(standId), standName, new LocationDTO(standLongitude, standLatitude)));
    }
}

package cz.kiv.pia.jdbc.repository;

import cz.kiv.pia.jdbc.repository.dto.BikeDTO;
import cz.kiv.pia.jdbc.repository.dto.LocationDTO;
import cz.kiv.pia.jdbc.repository.dto.RideDTO;
import cz.kiv.pia.jdbc.repository.dto.StandDTO;
import io.micrometer.common.lang.NonNull;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Class used for mapping data from database to RideDTO objects that are mapped later to Ride objects.
 * Take values by String key used in SQL query and retrieves data from ResultSet.
 */
@Service
public class RideRowMapper implements RowMapper<RideDTO> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final DateTimeFormatter bikeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public RideDTO mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getString("ride.id");
        var rideUserId = rs.getString("ride.userId");
        var bikeId = rs.getString("ride.bikeId");
        var startStandId = rs.getString("ride.startStandId");
        var endStandId = rs.getString("ride.endStandId");
        var startStandLongitude = rs.getString("startStand.longitude");
        var startStandLatitude = rs.getString("startStand.latitude");
        var startStandName = rs.getString("startStand.name");
        var endStandLongitude = rs.getString("endStand.longitude");
        var endStandLatitude = rs.getString("endStand.latitude");
        var endStandName = rs.getString("endStand.name");
        var bikeLongitude = rs.getString("bike.longitude");
        var bikeLatitude = rs.getString("bike.latitude");
        var bikeLastServiceDate = rs.getString("bike.lastServiceDate");
        var bikeStandId = rs.getString("bikeStand.id");
        var bikeStandLongitude = rs.getString("bikeStand.longitude");
        var bikeStandLatitude = rs.getString("bikeStand.latitude");
        var bikeStandName = rs.getString("bikeStand.name");
        var startTimestamp = rs.getString("ride.startTimestamp");
        var endTimestamp = rs.getString("ride.endTimestamp");
        var state = rs.getString("ride.state");
        return new RideDTO(Integer.parseInt(id), rideUserId, new BikeDTO(Integer.parseInt(bikeId),
                new LocationDTO(bikeLongitude, bikeLatitude), LocalDate.parse(bikeLastServiceDate, bikeformatter),
                new StandDTO(Integer.parseInt(bikeStandId), bikeStandName, new LocationDTO(bikeStandLongitude, bikeStandLatitude))),
                new StandDTO(Integer.parseInt(startStandId), startStandName, new LocationDTO(startStandLongitude, startStandLatitude)),
                new StandDTO(Integer.parseInt(endStandId), endStandName, new LocationDTO(endStandLongitude, endStandLatitude)),
                LocalDateTime.parse(startTimestamp, formatter), LocalDateTime.parse(endTimestamp, formatter), state);
    }
}

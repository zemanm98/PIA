package cz.kiv.pia.jdbc.repository;

import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.domain.Location;
import cz.kiv.pia.domain.Ride;
import cz.kiv.pia.domain.Stand;
import cz.kiv.pia.jdbc.repository.dto.RideDTO;
import cz.kiv.pia.repository.RideRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
/**
 * Jdbc template engine used for communication with MySQL database. Specifically with ride table.
 */
@Primary
@Repository
public class JdbcTemplateRideRepository implements RideRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<RideDTO> rowMapper;

    public JdbcTemplateRideRepository(JdbcTemplate jdbcTemplate, RowMapper<RideDTO> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    /**
     * Query returns all Rides ridden by user with given user id.
     * @param userId - id of a user
     * @return - Rides ridden by user with given user id.
     */
    @Override
    public Collection<Ride> getAllUserRides(String userId) {
        var sql = """
                SELECT ride.id, ride.userId, ride.bikeId, ride.startStandId, ride.endStandId, startStand.id, startStand.longitude, startStand.latitude,
                startStand.name, endStand.id, endStand.longitude, endStand.latitude, endStand.name, bike.id,
                bike.longitude, bike.standId, bike.latitude, bike.lastServiceDate, bikeStand.id, bikeStand.longitude,
                bikeStand.latitude, bikeStand.name, ride.startTimestamp, ride.endTimestamp, ride.state
                FROM ride INNER JOIN stand AS startStand ON ride.startStandId = startStand.id
                INNER JOIN stand AS endStand ON ride.endStandId = endStand.id
                INNER JOIN bike ON ride.bikeId = bike.id
                INNER JOIN stand AS bikeStand ON bike.standId = bikeStand.id
                WHERE ride.userId = ?
                """;
        var result = jdbcTemplate.query(sql, rowMapper, userId);
        return result.stream()
                .map(rideDTO -> {
                    var id = rideDTO.id();
                    var user = rideDTO.user();
                    var bike = new Bike(rideDTO.bike().id(), new Location(rideDTO.bike().location().longitude(),
                            rideDTO.bike().location().latitude()), rideDTO.bike().lastServiceDate(), new Stand(rideDTO.bike().stand().id(),
                            rideDTO.bike().stand().name(), new Location(rideDTO.bike().stand().location().longitude(), rideDTO.bike().stand().location().latitude())));
                    var state = Ride.State.valueOf(rideDTO.state());
                    var startTimeStamp = rideDTO.startTimestamp();
                    var startStand = new Stand(rideDTO.startStand().id(), rideDTO.startStand().name(),
                            new Location(rideDTO.startStand().location().longitude(), rideDTO.startStand().location().latitude()));
                    var endTimeStamp = rideDTO.endTimestamp();
                    var endStand = new Stand(rideDTO.endStand().id(), rideDTO.endStand().name(),
                            new Location(rideDTO.endStand().location().longitude(), rideDTO.endStand().location().latitude()));
                    return new Ride(id, user, bike, state, startTimeStamp, startStand, endTimeStamp, endStand);
                })
                .toList();
    }

    /**
     * Inserts new ride into ride table. end timestamp and end stand are kept null.
     * @param userId - user id
     * @param bikeId - bike id
     * @param startStandId - start stand id
     * @param startTimestamp - start time stamp
     * @param state - state (STARTED)
     * @return - success indicator
     */
    @Override
    public int createRide(String userId, int bikeId, int startStandId, LocalDateTime startTimestamp, String state) {
        var sql = """
                INSERT INTO ride (userId, bikeId, startStandId, endStandId, startTimeStamp, endTimeStamp, state)
                VALUES (?, ?, ?, NULL, ?, NULL, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userId);
            ps.setInt(2, bikeId);
            ps.setInt(3, startStandId);
            ps.setTimestamp(4, Timestamp.valueOf(startTimestamp.plusHours(1)));
            ps.setString(5, state);
            return ps;
        }, keyHolder);
        try{
            return keyHolder.getKey().intValue();
        }catch (NullPointerException e){
            return 0;
        }
    }

    /**
     * Updates created ride and ends it with given end timestamp and end stand.
     * @param rideId - id of STARTED ride
     * @param standId - end stand id
     * @param endTimeStamp - end timestamp
     * @param state - end state (COMPLETED)
     * @return - success indicator
     */
    @Override
    public int endRide(int rideId, int standId, LocalDateTime endTimeStamp, String state) {
        var sql = """
                UPDATE ride SET endStandId = ?, endTimestamp = ?, state = ? WHERE id = ?
                """;
        return jdbcTemplate.update(sql, standId, endTimeStamp, state, rideId);
    }
}

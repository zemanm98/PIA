package cz.kiv.pia.jdbc.repository;

import cz.kiv.pia.domain.Bike;
import cz.kiv.pia.domain.Location;
import cz.kiv.pia.domain.Stand;
import cz.kiv.pia.jdbc.repository.dto.BikeDTO;
import cz.kiv.pia.jdbc.repository.dto.StandDTO;
import cz.kiv.pia.repository.BikeRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Jdbc template engine used for communication with MySQL database. Specifically with bike table.
 */
@Primary
@Repository
public class JdbcTemplateBikeRepository implements BikeRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<BikeDTO> rowMapper;

    public JdbcTemplateBikeRepository(JdbcTemplate jdbcTemplate, BikeRowMapper standRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = standRowMapper;
    }

    /**
     * Returns collection of all bikes in this system.
     * @return - all bikes.
     */
    @Override
    public Collection<Bike> getAll() {
        var sql = """
                SELECT stand.id, stand.longitude, stand.latitude, stand.name, bike.id, bike.longitude, bike.standId,
                bike.latitude, bike.lastServiceDate, bike.inUse
                FROM bike INNER JOIN stand ON bike.standId = stand.id
                """;
        var result = jdbcTemplate.query(sql, rowMapper);
        return result.stream()
                .map(bikeDTO -> {
                    var id = bikeDTO.id();
                    var lastServiceTimestamp = bikeDTO.lastServiceDate();
                    var location = new Location(bikeDTO.location().longitude(), bikeDTO.location().latitude());
                    var standLocation = new Location(bikeDTO.stand().location().longitude(), bikeDTO.location().latitude());
                    var stand = new Stand(bikeDTO.stand().id(), bikeDTO.stand().name(), standLocation);
                    return new Bike(id, location, lastServiceTimestamp, stand);
                })
                .toList();
    }

    /**
     * Returns collection of all bikes with stand specified by given stand id. Bikes must be not in use
     * @param StandId - id of stand
     * @return - bikes with given stand
     */
    @Override
    public Collection<Bike> getBikesByStandId(int StandId) {
        var sql = """
                SELECT stand.id, stand.longitude, stand.latitude, stand.name, bike.id, bike.longitude, bike.standId,
                bike.latitude, bike.lastServiceDate, bike.inUse
                FROM bike INNER JOIN stand ON bike.standId = stand.id WHERE bike.standId = ? AND bike.inUse = 0
                """;
        var result = jdbcTemplate.query(sql, rowMapper, StandId);
        return result.stream()
                .map(bikeDTO -> {
                    var id = bikeDTO.id();
                    var lastServiceTimestamp = bikeDTO.lastServiceDate();
                    var location = new Location(bikeDTO.location().longitude(), bikeDTO.location().latitude());
                    var standLocation = new Location(bikeDTO.stand().location().longitude(), bikeDTO.location().latitude());
                    var stand = new Stand(bikeDTO.stand().id(), bikeDTO.stand().name(), standLocation);
                    return new Bike(id, location, lastServiceTimestamp,stand);
                })
                .toList();
    }

    /**
     * Updates specific bike with new last service timestamp, making it repaired.
     * @param id - id of a bike
     * @param timeStamp - new last service timestamp
     * @return - success indicator
     */
    @Override
    public int repairBikeWithId(int id, LocalDate timeStamp) {
        var sql = """
                UPDATE bike SET lastServiceDate = ? WHERE id = ?
                """;
        return jdbcTemplate.update(sql, timeStamp, id);
    }

    /**
     * Returns one specific bike with given id
     * @param id - id of a bike
     * @return - Specific bike with given id.
     */
    @Override
    public Bike getBikeById(int id) {
        var sql = """
                SELECT stand.id, stand.longitude, stand.latitude, stand.name, bike.id, bike.longitude, bike.standId,
                bike.latitude, bike.lastServiceDate
                FROM bike INNER JOIN stand ON bike.standId = stand.id
                WHERE bike.id = ?;
                """;
        var result = jdbcTemplate.query(sql, rowMapper, id);
        return result.stream()
                .map(bikeDTO -> {
                    var bikeId = bikeDTO.id();
                    var lastServiceTimestamp = bikeDTO.lastServiceDate();
                    var location = new Location(bikeDTO.location().longitude(), bikeDTO.location().latitude());
                    var standLocation = new Location(bikeDTO.stand().location().longitude(), bikeDTO.location().latitude());
                    var stand = new Stand(bikeDTO.stand().id(), bikeDTO.stand().name(), standLocation);
                    return new Bike(bikeId, location, lastServiceTimestamp,stand);
                })
                .toList().get(0);
    }

    /**
     * Updates location of bike with given id.
     * @param id - id of a bike
     * @param location - new location of a bike.
     * @return - success indicator
     */
    @Override
    public int updateBike(int id, Location location) {
        var sql = """
                UPDATE bike SET longitude = ?, latitude = ? WHERE id = ?
                """;
        return jdbcTemplate.update(sql, String.valueOf(location.getLongitude()) + "E",
                String.valueOf(location.getLatitude()) + "N", id);
    }

    /**
     * Sets bike as in use.
     * @param id - id of a bike
     * @param inUse - 1 for marking as in use.
     * @return - success indicator
     */
    @Override
    public int setBikeInUse(int id, int inUse) {
        var sql = """
                UPDATE bike SET inUse = ? WHERE id = ?
                """;
        return jdbcTemplate.update(sql, inUse, id);
    }

    /**
     * Updates stand of a bike. This happens at the end of a ride so bike is also marked as not in use.
     * @param bikeId - bike ID
     * @param standId - ID of a new stand
     * @param inUse - 0 for marking as not in use
     * @return - success indicator
     */
    @Override
    public int updateBikeStand(int bikeId, int standId, int inUse) {
        var sql = """
                UPDATE bike SET inUse = ?, standId = ? WHERE id = ?
                """;
        return jdbcTemplate.update(sql, inUse, standId, bikeId);
    }
}

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

import java.util.Collection;
@Primary
@Repository
public class JdbcTemplateBikeRepository implements BikeRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<BikeDTO> rowMapper;

    public JdbcTemplateBikeRepository(JdbcTemplate jdbcTemplate, BikeRowMapper standRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = standRowMapper;
    }
    @Override
    public Collection<Bike> getAll() {
        var sql = """
                SELECT stand.id, stand.longitude, stand.latitude, stand.name, bike.id, bike.longitude, bike.standId,
                bike.latitude, bike.lastServiceDate
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
                    return new Bike(id, location, lastServiceTimestamp,stand);
                })
                .toList();
    }
}

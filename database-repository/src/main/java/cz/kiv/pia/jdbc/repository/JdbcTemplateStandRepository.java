package cz.kiv.pia.jdbc.repository;

import cz.kiv.pia.domain.Location;
import cz.kiv.pia.domain.Stand;
import cz.kiv.pia.jdbc.repository.dto.StandDTO;
import cz.kiv.pia.repository.StandRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * {@link JdbcTemplate}-based implementation of {@link StandRepository}.
 */
@Primary
@Repository
public class JdbcTemplateStandRepository implements StandRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<StandDTO> rowMapper;

    public JdbcTemplateStandRepository(JdbcTemplate jdbcTemplate, StandRowMapper standRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = standRowMapper;
    }

    /**
     * Returns all stands in system
     * @return - all stands
     */
    @Override
    public Collection<Stand> getAll() {
        var sql = """
                SELECT id, name, longitude, latitude FROM stand
                """;

        var result = jdbcTemplate.query(sql, rowMapper);

        return result.stream()
                .map(standDTO -> {
                    var id = standDTO.id();
                    var name = standDTO.name();
                    var location = new Location(standDTO.location().longitude(), standDTO.location().latitude());
                    return new Stand(id, name, location);
                })
                .toList();
    }

    /**
     * Returns specific stand by given stand id
     * @param id - id of a stand
     * @return - stand with given stand id.
     */
    @Override
    public Stand getStandById(int id) {
        var sql = """
                SELECT id, name, longitude, latitude FROM stand WHERE id = ?
                """;
        var result = jdbcTemplate.query(sql, rowMapper, id);
        return result.stream()
                .map(standDTO -> {
                    var standId = standDTO.id();
                    var name = standDTO.name();
                    var location = new Location(standDTO.location().longitude(), standDTO.location().latitude());
                    return new Stand(standId, name, location);
                })
                .toList().get(0);
    }
}

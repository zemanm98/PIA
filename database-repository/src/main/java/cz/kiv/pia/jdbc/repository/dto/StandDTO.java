package cz.kiv.pia.jdbc.repository.dto;

import java.util.List;

/**
 * Bike stand stored in database.
 *
 * @param id Unique bike stand identifier.
 * @param name Bike stand name.
 * @param location Bike stand location.
 */
public record StandDTO(
        int id,
        String name,
        LocationDTO location
) {}
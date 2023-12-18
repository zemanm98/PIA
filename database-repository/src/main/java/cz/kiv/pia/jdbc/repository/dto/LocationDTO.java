package cz.kiv.pia.jdbc.repository.dto;

/**
 * Data Transfer Object for Location. Object is mapped to Location when retrieved from database.
 * @param longitude - longitude of this location
 * @param latitude - latitude of this location
 */
public record LocationDTO(
        String longitude,
        String latitude
) {}

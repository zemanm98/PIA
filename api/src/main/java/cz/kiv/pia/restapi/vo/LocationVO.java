package cz.kiv.pia.restapi.vo;

import cz.kiv.pia.domain.Location;

/**
 * Value Object of Location for presentation layer.
 * @param longitude - longitude of a location
 * @param latitude - latitude of a location
 */
public record LocationVO(
        Double longitude,
        Double latitude
) {
    public LocationVO(Location location) {
        this(location.getLongitude(), location.getLatitude());
    }
}
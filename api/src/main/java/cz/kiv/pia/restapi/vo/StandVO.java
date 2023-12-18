package cz.kiv.pia.restapi.vo;

import cz.kiv.pia.domain.Stand;
import cz.kiv.pia.restapi.controller.HomeController;

/**
 * Value Object of Stand for presentation layer.
 * @param id - id of stand
 * @param name - name of this stand
 * @param location - location of this stand
 */
public record StandVO(
        int id,
        String name,
        LocationVO location
) {
    public StandVO(Stand stand) {
        this(stand.getId(), stand.getName(), new LocationVO(stand.getLocation()));
    }
}

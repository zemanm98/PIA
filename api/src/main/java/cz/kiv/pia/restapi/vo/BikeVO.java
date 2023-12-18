package cz.kiv.pia.restapi.vo;

import cz.kiv.pia.domain.Bike;

import java.time.LocalDate;

/**
 * Value Object used for presentation layer.
 * @param id - ID of a bike
 * @param location - Location of a bike
 * @param lastServiceStamp - Last service timestamp of a bike
 * @param stand - stand that this bike belongs to now.
 */
public record BikeVO(
        int id,
        LocationVO location,
        LocalDate lastServiceStamp,

        StandVO stand) {
    public BikeVO(Bike bike){this(bike.getId(), new LocationVO(bike.getLocation()),
            bike.getLastServiceDate(), new StandVO(bike.getStand()));}

}

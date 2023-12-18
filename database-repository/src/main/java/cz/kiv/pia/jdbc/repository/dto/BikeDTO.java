package cz.kiv.pia.jdbc.repository.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object for Bikes. Object is mapped to Bike when retrieved from database.
 * @param id - id of this bike
 * @param location - location of this bike
 * @param lastServiceDate - last service date of this bike
 * @param stand - stand this bike belongs to
 */
public record BikeDTO (
    int id,

    LocationDTO location,

    LocalDate lastServiceDate,

    StandDTO stand

){}

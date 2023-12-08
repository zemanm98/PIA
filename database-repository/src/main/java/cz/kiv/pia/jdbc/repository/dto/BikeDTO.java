package cz.kiv.pia.jdbc.repository.dto;

import java.time.LocalDate;

public record BikeDTO (
    int id,

    LocationDTO location,

    LocalDate lastServiceDate,

    StandDTO stand

){}

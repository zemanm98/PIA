package cz.kiv.pia.restapi.controller;

import cz.kiv.pia.api.controller.ApiApi;
import cz.kiv.pia.api.model.BikeDTO;
import cz.kiv.pia.api.model.LocationDTO;
import cz.kiv.pia.api.model.RideDTO;
import cz.kiv.pia.api.model.StandDTO;
import cz.kiv.pia.service.RideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiHistoryController implements ApiApi {
    private final RideService rideService;

    public ApiHistoryController(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public ResponseEntity<List<RideDTO>> userRideHistory(@PathVariable("userId") Integer userId){
        var result = rideService.getAllUserRides(String.valueOf(userId));

        List<RideDTO> dtos = new ArrayList<>();
        for(var ride : result){
            var bikeDTO = new BikeDTO()
                    .id(ride.getBike().getId())
                    .lastServiceStamp(ride.getBike().getLastServiceDate())
                    .location(new LocationDTO(ride.getBike().getLocation().getLongitude(), ride.getBike().getLocation().getLatitude()))
                    .stand(new StandDTO(ride.getBike().getStand().getId(), ride.getBike().getStand().getName(),
                                        new LocationDTO(ride.getBike().getStand().getLocation().getLongitude(),
                                                ride.getBike().getStand().getLocation().getLatitude())));
            var startStandDTO = new StandDTO()
                    .id(ride.getStartStand().getId())
                    .name(ride.getStartStand().getName())
                    .location(new LocationDTO().latitude(ride.getStartStand().getLocation().getLatitude())
                            .longitude(ride.getStartStand().getLocation().getLatitude()));
            var endStandDTO = new StandDTO()
                    .id(ride.getEndStand().getId())
                    .name(ride.getEndStand().getName())
                    .location(new LocationDTO().latitude(ride.getEndStand().getLocation().getLatitude())
                            .longitude(ride.getEndStand().getLocation().getLatitude()));
            var rideDTO = new RideDTO().bike(bikeDTO)
                    .startStand(startStandDTO)
                    .endStand(endStandDTO)
                    .startTimeStamp(OffsetDateTime.of(ride.getStartTimestamp(), ZoneOffset.UTC))
                    .endTimeStamp(OffsetDateTime.of(ride.getEndTimestamp(), ZoneOffset.UTC))
                    .state(ride.getState().toString());
            dtos.add(rideDTO);
        }
        return ResponseEntity.ok(dtos);
    }
}

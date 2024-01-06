package cz.kiv.pia.restapi.controller;

import cz.kiv.pia.api.controller.ApiApi;
import cz.kiv.pia.api.model.BikeDTO;
import cz.kiv.pia.api.model.LocationDTO;
import cz.kiv.pia.api.model.RideDTO;
import cz.kiv.pia.api.model.StandDTO;
import cz.kiv.pia.service.BikeService;
import cz.kiv.pia.service.RideService;
import cz.kiv.pia.service.StandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController implements ApiApi {

    private final BikeService bikeService;

    private final RideService rideService;

    private final StandService standService;

    public ApiController(BikeService bikeService, RideService rideService, StandService standService) {
        this.bikeService = bikeService;
        this.rideService = rideService;
        this.standService = standService;
    }

    @Override
    public ResponseEntity<List<BikeDTO>> retrieveBikes(){
        var result = bikeService.getAllRideableBikes();

        List<BikeDTO> dtos = new ArrayList<>();
        for (var bike : result) {
            var locationDTO = new LocationDTO()
                    .latitude(bike.getLocation().getLatitude())
                    .longitude(bike.getLocation().getLongitude());

            var standDTO = new StandDTO()
                    .id(bike.getStand().getId())
                    .name(bike.getStand().getName())
                    .location(locationDTO);

            var bikeDTO = new BikeDTO()
                    .id(bike.getId())
                    .stand(standDTO)
                    .location(locationDTO)
                    .lastServiceStamp(bike.getLastServiceDate());

            dtos.add(bikeDTO);
        }
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<List<BikeDTO>> retrieveStandBikes(@PathVariable("standId") Integer standId){
        var result = bikeService.getBikesByStandId(standId);
        List<BikeDTO> dtos = new ArrayList<>();
        for (var bike : result) {
            var locationDTO = new LocationDTO()
                    .latitude(bike.getLocation().getLatitude())
                    .longitude(bike.getLocation().getLongitude());

            var standDTO = new StandDTO()
                    .id(bike.getStand().getId())
                    .name(bike.getStand().getName())
                    .location(locationDTO);

            var bikeDTO = new BikeDTO()
                    .id(bike.getId())
                    .stand(standDTO)
                    .location(locationDTO)
                    .lastServiceStamp(bike.getLastServiceDate());

            dtos.add(bikeDTO);
        }
        return ResponseEntity.ok(dtos);
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

    @Override
    public ResponseEntity<List<BikeDTO>> retrieveBikesForReapir(){
        var result = bikeService.getAllBikesToRepair();
        List<BikeDTO> dtos = new ArrayList<>();
        for (var bike : result) {
            var locationDTO = new LocationDTO()
                    .latitude(bike.getLocation().getLatitude())
                    .longitude(bike.getLocation().getLongitude());

            var standDTO = new StandDTO()
                    .id(bike.getStand().getId())
                    .name(bike.getStand().getName())
                    .location(locationDTO);

            var bikeDTO = new BikeDTO()
                    .id(bike.getId())
                    .stand(standDTO)
                    .location(locationDTO)
                    .lastServiceStamp(bike.getLastServiceDate());

            dtos.add(bikeDTO);
        }
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<String> repairBike(@PathVariable("bikeId") Integer bikeId){
        var result = bikeService.RepairBikeWithId(bikeId);
        return ResponseEntity.ok("Bike with id" + String.valueOf(bikeId) + "repaired");
    }

    @Override
    public ResponseEntity<List<StandDTO>> retrieveStands(){
        var result = standService.getAll();

        List<StandDTO> dtos = new ArrayList<>();
        for(var stand : result){
            var standDTO = new StandDTO()
                    .id(stand.getId())
                    .name(stand.getName())
                    .location(new LocationDTO().longitude(stand.getLocation().getLongitude()).latitude(stand.getLocation().getLatitude()));
            dtos.add(standDTO);
        }
        return ResponseEntity.ok(dtos);
    }

}

package cz.kiv.pia.restapi.controller;

import cz.kiv.pia.api.controller.ApiApi;
import cz.kiv.pia.api.model.BikeDTO;
import cz.kiv.pia.api.model.LocationDTO;
import cz.kiv.pia.api.model.StandDTO;
import cz.kiv.pia.service.BikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiRepairController implements ApiApi {

    private final BikeService bikeService;

    public ApiRepairController(BikeService bikeService) {
        this.bikeService = bikeService;
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
}
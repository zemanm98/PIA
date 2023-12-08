package cz.kiv.pia.restapi.controller;

import cz.kiv.pia.api.controller.BikesApi;
import cz.kiv.pia.api.model.BikeDTO;
import cz.kiv.pia.api.model.LocationDTO;
import cz.kiv.pia.api.model.StandDTO;
import cz.kiv.pia.service.BikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BikeController implements BikesApi {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {this.bikeService = bikeService;}


    @Override
    public ResponseEntity<List<BikeDTO>> retrieveBikes(){
        var result = this.bikeService.getAll();

        List<BikeDTO> dtos = new ArrayList<>();

        for(var bike : result){
            var LocationDTO = new LocationDTO()
                    .latitude(bike.getLocation().getLatitude())
                    .longitude(bike.getLocation().getLongitude());
            var StandDTO = new StandDTO()
                    .id(bike.getStand().getId())
                    .name(bike.getStand().getName())
                    .location(LocationDTO);
            var BikeDTO = new BikeDTO()
                    .id(bike.getId())
                    .location(LocationDTO)
                    .lastServiceDate(bike.getLastServiceDate())
                    .stand(StandDTO);
            dtos.add(BikeDTO);
        }
        return ResponseEntity.ok(dtos);
    }
}

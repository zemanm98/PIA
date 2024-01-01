package cz.kiv.pia.restapi.controller;

import cz.kiv.pia.api.controller.ApiApi;
import cz.kiv.pia.api.model.BikeDTO;
import cz.kiv.pia.api.model.LocationDTO;
import cz.kiv.pia.api.model.StandDTO;
import cz.kiv.pia.service.StandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiStandController implements ApiApi {

    private final StandService standService;

    public ApiStandController(StandService standService) {
        this.standService = standService;
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

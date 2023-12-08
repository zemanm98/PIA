package cz.kiv.pia.restapi.controller;

import cz.kiv.pia.api.controller.StandsApi;
import cz.kiv.pia.api.model.LocationDTO;
import cz.kiv.pia.api.model.StandDTO;
import cz.kiv.pia.service.StandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StandController implements StandsApi {

    private final StandService standService;

    public StandController(StandService standService) {
        this.standService = standService;
    }

    @Override
    public ResponseEntity<String> retrieveStands() {
        var result = standService.getAll();

        List<StandDTO> dtos = new ArrayList<>();
        for (var stand : result) {
            var locationDTO = new LocationDTO()
                    .latitude(stand.getLocation().getLatitude())
                    .longitude(stand.getLocation().getLongitude());

            var standDTO = new StandDTO()
                    .id(stand.getId())
                    .name(stand.getName())
                    .location(locationDTO);

            dtos.add(standDTO);
        }

        return ResponseEntity.ok("jajs");
    }

    @Override
    public ResponseEntity<StandDTO> retrieveStandBikes(Integer standId){
        var result = standService.getStand(standId);
        return null;
    }
}

package cz.kiv.pia.restapi.controller;

import cz.kiv.pia.domain.*;
import cz.kiv.pia.restapi.communication.EndRideMessage;
import cz.kiv.pia.restapi.communication.LocationMessage;
import cz.kiv.pia.restapi.communication.RideMessage;
import cz.kiv.pia.restapi.vo.UserVO;
import cz.kiv.pia.service.BikeService;
import cz.kiv.pia.service.RideService;
import cz.kiv.pia.service.StandService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class RideController {
    /**
     * Service used for retrieving and storing data about rides.
     */
    private final RideService rideService;
    /**
     * Service used for retrieving and storing data about bikes.
     */
    private final BikeService bikeService;
    /**
     * Service used for retrieving and storing data about stands.
     */
    private final StandService standService;

    public RideController(RideService rideService, BikeService bikeService, StandService standService) {
        this.rideService = rideService;
        this.bikeService = bikeService;
        this.standService = standService;
    }

    /**
     * Method creatyes data for a view where user can ride selected bike. Websocket communication via STOMP library is used for
     * updating location o bike.
     * @param model - thymeleaf model for generating html response
     * @param id - ID of a bike thats about to be ridden.
     * @param principal - authentification token provided by 0Auth2.0.
     * @param authentication - authentification token provided by spring in CustomAuthentificationProvider.
     * @return - html template generated by thymeleaf
     */
    @GetMapping("/bikes/ride/{bikeId}")
    public String rideCertainBike(Model model, @PathVariable("bikeId") String id,
                                  @AuthenticationPrincipal OidcUser principal,
                                  Authentication authentication){
        // providing user specific data
        if (principal != null) {
            String userId = principal.getClaims().get("sub").toString();
            String name = principal.getClaims().get("name").toString();
            String role = principal.getAttributes().get("https..//localhost.8080/roles").toString();
            role = role.substring(1, role.length() - 1);
            if(role.length() == 0){
                role = "REGULAR";
            }
            model.addAttribute("profile", new UserVO(userId, name, role));
        }
        if (authentication instanceof UsernamePasswordAuthenticationToken){
            String[] data = authentication.getPrincipal().toString().split(";");
            model.addAttribute("profile", new UserVO(data[0], data[1], data[2]));
        }
        model.addAttribute("bikeId", id);
        return "rideBike";
    }

    /**
     * Message subscriber for starting a ride by a user. Method calls bike and ride service. Marks bike as inUse and creates
     * STARTED ride with given user and bike.
     * @param message - message with data about user and bike that started this ride.
     * @return - message with ID of newly created ride. Client stores it for later use in ending the ride. If ride was
     * not possible to create returns 0.
     */
    @MessageMapping("/startRide")
    @SendTo("/update/rideStarted")
    public String startRide(RideMessage message) {
        Bike bike = bikeService.getBikeById(Integer.parseInt(message.getBikeId()));
        bikeService.setBikeInUse(Integer.parseInt(message.getBikeId()));
        int rideId = rideService.createRide(message.getUserId(), bike);
        if(rideId != 0){
            return String.valueOf(rideId);
        }
        return "0";
    }

    /**
     * Message subscriber for updating bike location that is currently ridden via websocket.
     * @param message - current location of a bike
     * @return - returns ID of stand that is within 50 meters of current location.
     */
    @MessageMapping("/update")
    @SendTo("/update/location")
    public String updateRide(LocationMessage message) {
        bikeService.updateBike(Integer.parseInt(message.getBikeId()),
                new Location(Double.parseDouble(message.getLongitude()), Double.parseDouble(message.getLatitude())));
        Collection<Stand> stands = standService.getAll();
        for(Stand stand : stands){
            if(distance(stand.getLocation().getLatitude(), Double.parseDouble(message.getLatitude()),
                    stand.getLocation().getLongitude(), Double.parseDouble(message.getLongitude())) <= 50){
                return String.valueOf(stand.getId());
            }
        }
        return "0";
    }

    /**
     * Method thats subscribed for messages that are ending a certain ride.
     * @param message - message for ending a ride containing ride id and stand id of the final stand.
     */
    @MessageMapping("/end")
    @SendTo("/update/location")
    public String endRide(EndRideMessage message) {
        bikeService.updateBikeStand(Integer.parseInt(message.getBikeId()), Integer.parseInt(message.getStandId()));
        rideService.endRide(Integer.parseInt(message.getRideId()), Integer.parseInt(message.getStandId()));
        return "";
    }

    /**
     * Method used for calculating distance between two given points by latitude and longitude
     * @return - distance between points in meters.
     */
    private double distance(double lat1, double lat2, double lon1, double lon2) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers.
        double r = 6371;

        // calculate the result in meters#
        return (c * r) * 1000;
    }
}

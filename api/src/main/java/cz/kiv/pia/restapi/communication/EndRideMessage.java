package cz.kiv.pia.restapi.communication;

public class EndRideMessage {
    /**
     * ID of bike that was used for ride thats ending.
     */
    private String bikeId;
    /**
     * ID of ending ride
     */
    private String rideId;
    /**
     * ID of a final stand for this ride.
     */
    private String standId;

    public EndRideMessage() {}

    public EndRideMessage(String bikeId, String rideId, String standId) {
        this.bikeId = bikeId;
        this.rideId = rideId;
        this.standId = standId;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }
}

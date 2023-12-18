package cz.kiv.pia.restapi.communication;

public class RideMessage {
    /**
     * ID of a bike with which a new ride is being started.
     */
    private String bikeId;
    /**
     * OD of a user that started a ride.
     */
    private String userId;

    public RideMessage(){}

    public RideMessage(String rideId){
        this.bikeId = rideId;
    }

    public RideMessage(String bike, String user){
        this.bikeId = bike;
        this.userId = user;
    }
    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

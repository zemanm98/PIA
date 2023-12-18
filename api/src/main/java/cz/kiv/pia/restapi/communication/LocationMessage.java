package cz.kiv.pia.restapi.communication;

public class LocationMessage {
    /**
     * ID of a bike thats location needs to be updated.
     */
    private String bikeId;
    /**
     * Updated latitude of a bike
     */
    private String latitude;
    /**
     * Updated longitude of a bike.
     */
    private String longitude;

    public LocationMessage() { }

    public LocationMessage(String bikeId, String latitude, String longitude) {
        this.bikeId = bikeId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String rideId) {
        this.bikeId = rideId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

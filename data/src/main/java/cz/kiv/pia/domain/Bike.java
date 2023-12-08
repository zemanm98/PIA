package cz.kiv.pia.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

/**
 * Class representing a single bike.
 */
public class Bike {
    /**
     * Unique identifier
     */
    private final int id;
    /**
     * Current location of the bike, updated in real-time.
     */
    private Location location;
    /**
     * Date of the last service.
     */
    private LocalDate lastServiceDate;
    /**
     * Stand that the bike is currently placed at. Can be null if the bike is ridden atm.
     */
    private Stand stand;

    // constructor used when a reference to Bike is needed in other object but full Bike object is not loaded from storage
    public Bike(int id) {
        this.id = id;
    }

    // constructor used when full Bike object is loaded from storage
    public Bike(int id, Location location, LocalDate lastServiceTimestamp, Stand stand) {
        this.id = id;
        this.location = location;
        this.lastServiceDate = lastServiceTimestamp;
        this.stand = stand;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    public Stand getStand() {
        return stand;
    }

    /**
     * Checks whether this bike is due for service.
     *
     * @param serviceInterval Maximum service interval
     * @return True if bike is due for service, false otherwise
     */
    public boolean isDueForService(Period serviceInterval) {
        LocalDate nextServiceTimestamp = lastServiceDate.plus(serviceInterval);

        return LocalDate.now().isAfter(nextServiceTimestamp);
    }

    /**
     * Marks this bike as serviced now.
     */
    public void markServiced() {
        this.lastServiceDate = LocalDate.now();
    }

    /**
     * Adds this bike to given stand.
     *
     * @param stand Stand to add the bike to
     */
    public void addToStand(Stand stand) {
        this.stand = stand;
        this.stand.addBike(this);
    }

    /**
     * Removes this bike from its current stand.
     */
    public void removeFromStand() {
        this.stand.removeBike(this);
        this.stand = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bike bike)) return false;
        return Objects.equals(id, bike.id) && Objects.equals(location, bike.location) && Objects.equals(lastServiceDate, bike.lastServiceDate) && Objects.equals(stand, bike.stand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, lastServiceDate, stand);
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", location=" + location +
                ", lastServiceTimestamp=" + lastServiceDate +
                ", stand=" + stand +
                '}';
    }
}

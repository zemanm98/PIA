package cz.kiv.pia.exceptions;

import cz.kiv.pia.domain.Ride;

/**
 * Thrown when {@link Ride} is not found.
 */
public class RideNotFoundException extends IllegalStateException {
    public final Ride ride;

    public RideNotFoundException(Ride ride) {
        super("Ride %s was not found.".formatted(ride));
        this.ride = ride;
    }
}

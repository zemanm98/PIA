package cz.kiv.pia.repository;

import cz.kiv.pia.domain.Bike;

import java.util.Collection;

public interface BikeRepository {

    Collection<Bike> getAll();
}

package io.fuber.registory;

import org.springframework.data.jpa.repository.JpaRepository;

import io.fuber.model.Trip;

public interface TripRegistory extends JpaRepository<Trip,Integer> {

}

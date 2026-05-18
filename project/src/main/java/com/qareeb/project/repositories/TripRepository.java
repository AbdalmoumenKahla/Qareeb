package com.qareeb.project.repositories;

import com.qareeb.project.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
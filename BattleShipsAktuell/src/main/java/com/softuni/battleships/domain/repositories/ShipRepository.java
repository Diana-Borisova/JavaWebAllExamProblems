package com.softuni.battleships.domain.repositories;

import com.softuni.battleships.domain.entities.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship,Long> {
   Optional<Ship> findByName(String name);
   Optional<List<Ship>> findAllByUserId(Long id);
}

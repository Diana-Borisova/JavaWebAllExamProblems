package com.example.battle_ship.repository;

import com.example.battle_ship.model.entity.Ship;
import com.example.battle_ship.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    @Query("SELECT s from Ship s join User u where s.user.id = :id")
    Optional<List<Ship>> getShipsByUserId (Long id);

    @Query("SELECT s from Ship s join User u where s.user.id not in :id")
    Optional<List<Ship>> getShipsByUserIdNot (Long id);


    Optional<List<Ship>> getShipsByUserNot(User user);
}

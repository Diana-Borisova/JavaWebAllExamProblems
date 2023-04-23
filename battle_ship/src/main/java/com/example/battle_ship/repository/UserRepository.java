package com.example.battle_ship.repository;

import com.example.battle_ship.model.entity.Category;
import com.example.battle_ship.model.entity.Ship;
import com.example.battle_ship.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findById(Long id);

    Optional<User> findByIdNot(Long id);

    Optional<List<User>> findAllByIdNot(Long id);

    @Query("select u from User u join Ship s where s.user.id = :id")
    Optional<User> findUserByShipId(Long id);
}

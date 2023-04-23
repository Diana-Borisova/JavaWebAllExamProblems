package com.example.modelmapper.repositories;

import com.example.modelmapper.dtos.UserLoginDto;
import com.example.modelmapper.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    String findByTitle(String title);
    Game findGameById(long id);
    String deleteById(long id);
    @Query("Select g from Game g")
    Set<Game> findAllBy();

}

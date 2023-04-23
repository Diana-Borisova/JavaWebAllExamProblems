package com.example.battle_ship.model.views;

import com.example.battle_ship.model.entity.Ship;
import com.example.battle_ship.model.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipsViewModel  {
private Long id;
    private String name;
    private Long health;
    private Long power;

    List<Ship> defenderList;
    private User user;
}

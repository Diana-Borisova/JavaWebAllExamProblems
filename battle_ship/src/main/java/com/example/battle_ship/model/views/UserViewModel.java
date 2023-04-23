package com.example.battle_ship.model.views;

import com.example.battle_ship.model.entity.Ship;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserViewModel {

    private Long id;
    private List<Ship> ships;

    public UserViewModel() {
        this.ships = new ArrayList<>();
    }
}

package com.softuni.battleships.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BattleShipsModel {
    private Long loggedUserShip;
    private Long notLoggedUserShip;
}

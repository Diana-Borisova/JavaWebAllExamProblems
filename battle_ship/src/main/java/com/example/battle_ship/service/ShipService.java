package com.example.battle_ship.service;


import com.example.battle_ship.model.entity.Ship;
import com.example.battle_ship.model.services.BattleShipsModel;
import com.example.battle_ship.model.services.ShipServiceModel;
import com.example.battle_ship.model.views.ShipsViewModel;

import java.util.List;

public interface ShipService {

    void addShip(ShipServiceModel shipServiceModel);

    List<ShipsViewModel> findAllShips();

    List<ShipsViewModel> getShipsByUserId(Long id);

    List<ShipsViewModel> getShipsByUserNot ();

    void fight(BattleShipsModel battleShipsModel);
}

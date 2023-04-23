package com.example.battle_ship.service.impl;

import com.example.battle_ship.model.entity.Ship;
import com.example.battle_ship.model.entity.User;
import com.example.battle_ship.model.services.BattleShipsModel;
import com.example.battle_ship.model.services.ShipServiceModel;
import com.example.battle_ship.model.views.ShipsViewModel;
import com.example.battle_ship.repository.ShipRepository;
import com.example.battle_ship.service.CategoryService;
import com.example.battle_ship.service.ShipService;
import com.example.battle_ship.service.UserService;
import com.example.battle_ship.utils.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    private final ShipRepository shipRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    private final CategoryService categoryService;
    private final UserService userService;

    public ShipServiceImpl(ShipRepository shipRepository, LoggedUser loggedUser, ModelMapper modelMapper, CategoryService categoryService, UserService userService) {
        this.shipRepository = shipRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void addShip(ShipServiceModel shipServiceModel) {
        Ship ship = modelMapper.map(shipServiceModel, Ship.class);
        ship.setCategory(this.categoryService.findCategoryByName(shipServiceModel.getCategory()));
        ship.setCreated(shipServiceModel.getCreated());
        ship.setName(shipServiceModel.getName());
        ship.setHealth(shipServiceModel.getHealth());
        ship.setUser(this.userService.findById(loggedUser.getId()));
        ship.setPower(shipServiceModel.getPower());
        this.shipRepository.save(ship);
    }

    @Override
    public List<ShipsViewModel> findAllShips() {
        return this.shipRepository.findAll()
                .stream()
                .map(ship -> modelMapper.map(ship,ShipsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipsViewModel> getShipsByUserId(Long id) {

        return this.shipRepository.getShipsByUserId(id).orElse(null)
                .stream()
                .map(ship -> modelMapper.map(ship,ShipsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipsViewModel> getShipsByUserNot() {
        return null;
    }

    @Override
    public void fight(BattleShipsModel battleShipsModel) {
        Ship loggedShip = this.shipRepository.findById(battleShipsModel.getLoggedUserShip()).orElseThrow();
        Ship notLoggedShip = this.shipRepository.findById(battleShipsModel.getNotLoggedUserShip()).orElseThrow();

        notLoggedShip.setHealth(notLoggedShip.getHealth() - loggedShip.getPower());

        if (notLoggedShip.getHealth() <= 0) {
            this.shipRepository.deleteById(battleShipsModel.getNotLoggedUserShip());
        } else {
            this.shipRepository.saveAndFlush(notLoggedShip);
        }
    }




    public List<ShipsViewModel> getShipsByUserIdNot(Long id) {
       User user = this.userService.findById(loggedUser.getId());
        return this.shipRepository.getShipsByUserIdNot(id).orElse(null)
                .stream()
                .map(ship -> modelMapper.map(ship,ShipsViewModel.class))
                .collect(Collectors.toList());
    }
}

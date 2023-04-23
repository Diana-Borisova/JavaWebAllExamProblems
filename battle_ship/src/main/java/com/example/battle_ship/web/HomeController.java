package com.example.battle_ship.web;

import com.example.battle_ship.model.entity.Ship;
import com.example.battle_ship.model.services.BattleShipsModel;
import com.example.battle_ship.model.views.ShipsViewModel;
import com.example.battle_ship.service.ShipService;
import com.example.battle_ship.service.UserService;
import com.example.battle_ship.utils.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
private final LoggedUser loggedUser;
private final ShipService shipService;
private final UserService userService;
private  final ModelMapper modelMapper;

    public HomeController(LoggedUser loggedUser, ShipService shipService, UserService userService, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.shipService = shipService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("home")
    public String index(Model model){
        if (loggedUser.getId()== null){
            return "index";
        }
        List<ShipsViewModel> ships = shipService.findAllShips();

        model.addAttribute("ships", ships);
        model.addAttribute("attackerShips", shipService.getShipsByUserId(loggedUser.getId()));
        List<ShipsViewModel> defenderShips = new ArrayList<>();
        ships.forEach(shipsViewModel -> {
            if (!shipsViewModel.getUser().getId().equals(loggedUser.getId())) {
                defenderShips.add(shipsViewModel);
            }
        });
        model.addAttribute("defenderShips", defenderShips.stream().toList());

        return "home";
}



}


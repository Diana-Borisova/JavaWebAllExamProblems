package com.example.battle_ship.web;

import com.example.battle_ship.model.Dtos.ShipAddDto;
import com.example.battle_ship.model.services.ShipServiceModel;
import com.example.battle_ship.service.ShipService;
import com.example.battle_ship.utils.LoggedUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public ShipController(ShipService shipService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/add")
    public String add() {
        return "ship-add";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid ShipAddDto shipAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){

        redirectAttributes.addFlashAttribute("shipAddDto", shipAddDto);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipAddDto", bindingResult);
        return "redirect:add";
    }
        shipService.addShip(modelMapper.map(shipAddDto, ShipServiceModel.class));
        return "redirect:/home";
    }
    @ModelAttribute
    public ShipAddDto shipAddDto(){
        return new ShipAddDto();
    }
}
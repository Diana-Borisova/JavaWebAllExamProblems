package com.softuni.battleships.web;

import com.softuni.battleships.domain.model.ShipAddModel;
import com.softuni.battleships.domain.services.ShipService;
import jakarta.validation.Valid;
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

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/add")
    public String getAddShip(){
        return "ship-add";
    }

    @PostMapping("/add")
    public String postAddShip(@Valid @ModelAttribute(name = "shipAddModel")ShipAddModel shipAddModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipAddModel", shipAddModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.shipAddModel",
                            bindingResult);

            return "redirect:add";
        }

            this.shipService.addShip(shipAddModel);
            return "redirect:/home";
    }

    @ModelAttribute(name = "shipAddModel")
    public ShipAddModel shipAddModel() {
    return new ShipAddModel();
    }

}
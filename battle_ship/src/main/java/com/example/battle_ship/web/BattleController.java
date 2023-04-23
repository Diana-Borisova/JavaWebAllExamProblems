package com.example.battle_ship.web;

import com.example.battle_ship.model.Dtos.StartBattleDto;
import com.example.battle_ship.service.UserService;
import com.example.battle_ship.service.impl.BattleServiceImpl;
import com.example.battle_ship.utils.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BattleController {


        private final BattleServiceImpl battleService;
        private final UserService userService;
        private final LoggedUser loggedUser;

        public BattleController(BattleServiceImpl battleService, UserService userService, LoggedUser loggedUser) {
            this.battleService = battleService;
            this.userService = userService;
            this.loggedUser = loggedUser;
        }




        @PostMapping("/battle")
        public String battle(@Valid StartBattleDto startBattleDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws Exception {

            if (loggedUser.getId() == null) {
                return "redirect:/";
            }

            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("startBattleDTO", startBattleDTO);
                redirectAttributes.addFlashAttribute(
                        "org.springframework.validation.BindingResult.startBattleDTO", bindingResult);

                return "redirect:/home";
            }

            this.battleService.attack(startBattleDTO);

            return "redirect:/home";
        }

        @ModelAttribute
    public StartBattleDto startBattleDTO(){
            return new StartBattleDto();
        }
    }
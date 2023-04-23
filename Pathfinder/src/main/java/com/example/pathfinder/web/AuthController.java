package com.example.pathfinder.web;

import com.example.pathfinder.model.User;
import com.example.pathfinder.model.binding.UserRegisterBindingModel;
import com.example.pathfinder.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";

        }
        authService.register(userRegisterBindingModel);
        return "redirect:/login";
    }
        @GetMapping("/login")
        public String login () {
            //   model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            return "login";
        }

}
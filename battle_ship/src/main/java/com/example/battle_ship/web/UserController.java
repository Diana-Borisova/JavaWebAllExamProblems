package com.example.battle_ship.web;

import com.example.battle_ship.model.Dtos.UserLoginDto;
import com.example.battle_ship.model.Dtos.UserRegisterDto;
import com.example.battle_ship.model.entity.User;
import com.example.battle_ship.model.services.UserServiceModel;
import com.example.battle_ship.service.UserService;
import com.example.battle_ship.utils.LoggedUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public UserController(UserService userService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !userRegisterDto.getConfirmPassword().equals(userRegisterDto.getPassword())){
            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);

        return "redirect:register";
        }
        this.userService.registerUser(userRegisterDto);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model){
        if(!model.containsAttribute("isFound")){
            model.addAttribute("isFound", true);
        }

        return "login";
    }

    @PostMapping("/login")
    public String confirmLogin(@Valid UserLoginDto userLoginDto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterDto", userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto", bindingResult);

            return "redirect:login";
        }
        if (this.userService.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword()) == null){
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("isFound", false);

            return "redirect:login";
        }
        User user = this.userService.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());
        this.userService.login(user.getId());
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
    @ModelAttribute
    public UserRegisterDto userRegisterDto(){
        return new UserRegisterDto();
    }

    @ModelAttribute
    public UserLoginDto userLoginDto(){
        return new UserLoginDto();
    }
}

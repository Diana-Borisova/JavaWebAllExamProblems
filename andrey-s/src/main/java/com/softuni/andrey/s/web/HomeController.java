package com.softuni.andrey.s.web;

import com.softuni.andrey.s.entity.binding.ItemAddBindingModel;
import com.softuni.andrey.s.entity.view.ItemViewModel;
import com.softuni.andrey.s.service.ItemService;
import com.softuni.andrey.s.utils.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final ItemService itemService;

    public HomeController(LoggedUser loggedUser, ItemService itemService) {
        this.loggedUser = loggedUser;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index(Model model){
        if(loggedUser.getId() == null){
            return "index";
        }
        List<ItemViewModel> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("itemsCount" , items.size());
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model){
        if(loggedUser.getId() == null){
            return "redirect:/index";
        }
        List<ItemViewModel> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("itemsCount" , items.size());
        return "home";
    }




    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
    @ModelAttribute
    public ItemViewModel itemViewModel(){
        return new ItemViewModel();
    }

    @ModelAttribute
    public ItemAddBindingModel itemAddBindingModel(){
        return new ItemAddBindingModel();
    }
}

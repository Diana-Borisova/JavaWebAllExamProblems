package com.softuni.andrey.s.web;

import com.softuni.andrey.s.entity.Item;
import com.softuni.andrey.s.entity.binding.ItemAddBindingModel;
import com.softuni.andrey.s.entity.services.ItemServiceModel;
import com.softuni.andrey.s.service.ItemService;
import com.softuni.andrey.s.utils.LoggedUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ItemController {
    private final LoggedUser loggedUser;
    private final ItemService itemService;
    private final ModelMapper modelMapper;

    public ItemController(LoggedUser loggedUser, ItemService itemService, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add() {

        return "add-product";
    }
    @PostMapping("/add")
    public String addConfirm(@Valid ItemAddBindingModel itemAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }
        itemService.addOrder(modelMapper.map(itemAddBindingModel, ItemServiceModel.class));
        return "redirect:/";
    }
    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable String id , ModelAndView model) {

        Item item = this.itemService.findById(id);

        model.setViewName("details-product");
        model.addObject("item", item);
        return model;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        itemService.deleteItemById(id);

        return "redirect:/home";
    }
    @ModelAttribute
    public ItemAddBindingModel itemAddBindingModel(){
        return new ItemAddBindingModel();
    }


}
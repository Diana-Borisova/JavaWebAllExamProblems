package com.resellerapp.controller;


import com.resellerapp.model.binding.OfferAddBindingModel;
import com.resellerapp.model.service.OfferServiceModel;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/add")
    public String add(Model model){
        if (!model.containsAttribute("offerAddBindingModel")){
            model.addAttribute("offerAddBindingModel", new OfferAddBindingModel());
        }
        return "offer-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid OfferAddBindingModel offerAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }
       offerService.addOrder(modelMapper.map(offerAddBindingModel, OfferServiceModel.class));
        return "redirect:/";
    }





}

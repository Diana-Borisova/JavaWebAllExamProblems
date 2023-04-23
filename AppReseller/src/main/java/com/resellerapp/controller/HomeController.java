package com.resellerapp.controller;

import com.resellerapp.model.service.UserServiceModel;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
    private final UserService userService;
    private final LoggedUser loggedUser;

    private final OfferService offerService;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelAndView modelAndView;

    public HomeController(UserService userService, LoggedUser loggedUser, OfferService offerService, OfferRepository offerRepository, UserRepository userRepository, ModelAndView modelAndView) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.offerService = offerService;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelAndView = modelAndView;
    }


    @GetMapping()
    public String index(Model model){
        if(loggedUser.getId() == null){
            return "index";
        }
        UserServiceModel user = this.userService.findByUsername(loggedUser.getUsername());

        model.addAttribute("username", this.userService.getUsername());
        model.addAttribute("myOffers", this.offerService.findMyOffers());
        model.addAttribute("otherOffers", this.offerService.findOtherOffers());
        model.addAttribute("boughtOffers", this.userService.findByUsername(user.getUsername()).getBoughtOffers());

           return "home";
    }

   /* @GetMapping("home")
    public ModelAndView getHome(ModelAndView modelAndView) {

        OfferViewModel notLoggedUserOffers = (OfferViewModel) offerRepository.findAllByUserId(this.userService.findByIdNot(loggedUser.getId()));
                .findByIdNot(loggedUser.getId())
                .getId());

        modelAndView.setViewName("home");
        modelAndView.addObject("loggedUserWithShips", loggedUserWithShips);
        modelAndView.addObject("notLoggedUserWithShips", notLoggedUserWithShips);

        return modelAndView;
    }*/
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id){

        if (loggedUser.getId() == null) {
            return "redirect:/users/login";
        }

        offerService.removeMyOffer(id);

        return "redirect:/";
    }

    @GetMapping("/offers/buy/{id}")
    public String buy( @PathVariable Long id){

        if (loggedUser.getId() == null) {

            return "redirect:/users/login";
        }

       offerService.buyOffer(id);

        return "redirect:/";
    }
}


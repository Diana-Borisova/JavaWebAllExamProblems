package com.spotifyplaylistapp.controller;

import com.spotifyplaylistapp.model.binding.SongAddBindingModel;
import com.spotifyplaylistapp.model.binding.SongsByGenreBindingModel;
import com.spotifyplaylistapp.model.entity.Style;
import com.spotifyplaylistapp.model.service.SongServiceModel;
import com.spotifyplaylistapp.model.view.SongViewModel;
import com.spotifyplaylistapp.service.SongService;
import com.spotifyplaylistapp.service.StyleService;
import com.spotifyplaylistapp.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;

@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final SongService songService;
    private final StyleService styleService;


    public HomeController(LoggedUser loggedUser, SongService songService, StyleService styleService) {
        this.loggedUser = loggedUser;
        this.songService = songService;
        this.styleService = styleService;

    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null){
            return "index";
        }
        model.addAttribute("songs", songService.getSongs());
        model.addAttribute("myPlaylist", songService.getPlaylist(loggedUser.getId()));
        model.addAttribute("totalTime", this.songService.getTotalDuration(loggedUser.getId()));



        return "home";
    }




    private Set<SongServiceModel> getSongsByGenre(Style style) {
        return this.songService.findByStyle_Name(style.getName());
    }
    @ModelAttribute
    public SongAddBindingModel songAddBindingModel(){
        return new SongAddBindingModel();
    }
    @ModelAttribute
    public SongViewModel songViewModel(){
        return new SongViewModel();
    }

    @ModelAttribute
    public SongsByGenreBindingModel songsByGenreBindingModel(){
        return new SongsByGenreBindingModel();
    }

}

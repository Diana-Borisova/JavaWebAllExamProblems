package com.spotifyplaylistapp.controller;
import com.spotifyplaylistapp.service.SongService;
import com.spotifyplaylistapp.service.StyleService;
import com.spotifyplaylistapp.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    private final LoggedUser loggedUser;
    private final SongService songService;


    public HomeController(LoggedUser loggedUser, SongService songService) {
        this.loggedUser = loggedUser;
        this.songService = songService;

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


}

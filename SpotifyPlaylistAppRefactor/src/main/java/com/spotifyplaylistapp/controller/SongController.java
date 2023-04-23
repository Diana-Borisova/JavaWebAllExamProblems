package com.spotifyplaylistapp.controller;

import com.spotifyplaylistapp.model.binding.SongAddBindingModel;
import com.spotifyplaylistapp.model.service.SongServiceModel;
import com.spotifyplaylistapp.service.SongService;
import com.spotifyplaylistapp.service.StyleService;
import com.spotifyplaylistapp.util.LoggedUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final ModelMapper modelMapper;
    private final StyleService styleService;
    private final LoggedUser loggedUser;

    public SongController(SongService songService, ModelMapper modelMapper, StyleService styleService, LoggedUser loggedUser) {
        this.songService = songService;
        this.modelMapper = modelMapper;
        this.styleService = styleService;
        this.loggedUser = loggedUser;
    }


    @GetMapping("/add")
    public String add(Model model){
        if (!model.containsAttribute("songAddBindingModel")) {

            model.addAttribute("songAddBindingModel", new SongAddBindingModel());
            model.addAttribute("isAdded", false);
        }
        return "song-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid SongAddBindingModel songAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("songAddBindingModel", songAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }
        SongServiceModel songServiceModel = this.songService.findByTitle(
                (songAddBindingModel().getTitle()));

        if (songServiceModel != null){
            redirectAttributes.addFlashAttribute("songAddBindingModel", songAddBindingModel);
            redirectAttributes.addFlashAttribute("isAdded", true);
            return "redirect:add";
        }

       songService.addSong(modelMapper.map(songAddBindingModel, SongServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/add-to-playlist/{id}")
    public String addButton(@PathVariable Long id){

        if (loggedUser.getId() == null) {
            return "redirect:/users/login";
        }

        songService.addSongToPlaylist(id);

        return "redirect:/";
    }

    @GetMapping("/remove")
    public String remove(){

        if (loggedUser.getId() == null) {
            return "redirect:/users/login";
        }

        songService.removeSongFromPlaylist(loggedUser.getId());

        return "redirect:/";
    }
    @ModelAttribute
    public SongAddBindingModel songAddBindingModel(){
        return new SongAddBindingModel();
    }




}

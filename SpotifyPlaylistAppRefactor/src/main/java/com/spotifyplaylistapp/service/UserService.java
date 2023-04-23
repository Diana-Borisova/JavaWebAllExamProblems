package com.spotifyplaylistapp.service;

import com.spotifyplaylistapp.model.binding.UserRegisterBindingModel;
import com.spotifyplaylistapp.model.entity.Song;
import com.spotifyplaylistapp.model.entity.Style;
import com.spotifyplaylistapp.model.entity.StyleEnum;
import com.spotifyplaylistapp.model.entity.User;
import com.spotifyplaylistapp.model.service.SongServiceModel;
import com.spotifyplaylistapp.model.service.UserServiceModel;

import java.util.Optional;
import java.util.Set;


public interface UserService {

    void registerUser(UserServiceModel userServiceModel);

    void loginUser(Long id);

    UserServiceModel findByUsernameAndPassword(String username, String password);

     void addSongToUser(Long userId, Song song);

     User findById(Long id);

    void save(User user);


}

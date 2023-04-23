package com.spotifyplaylistapp.service.impl;

import com.spotifyplaylistapp.model.entity.Song;
import com.spotifyplaylistapp.model.entity.User;
import com.spotifyplaylistapp.model.service.SongServiceModel;
import com.spotifyplaylistapp.model.service.UserServiceModel;
import com.spotifyplaylistapp.repository.UserRepository;
import com.spotifyplaylistapp.service.UserService;
import com.spotifyplaylistapp.util.LoggedUser;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Getter
@Setter
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        this.userRepository.save(modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public void loginUser(Long id) {
        loggedUser.setId(id);
       // loggedUser.isLogged();
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return modelMapper.map(this.userRepository.findByUsernameAndPassword(username, password), UserServiceModel.class);
    }

    @Override
    public void addSongToUser(Long userId, Song song) {

            User user= this.userRepository.findById(userId).orElse(null);
            if (user != null){
            if (user.getPlaylist().stream().noneMatch(s -> s.getId().equals(song.getId()))) {
                user.addSongToPlaylist(song);
                modelMapper.map(this.userRepository.save(user), UserServiceModel.class);

        }}
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }


}

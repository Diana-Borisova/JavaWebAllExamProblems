package com.example.pathfinder.service;

import com.example.pathfinder.model.User;
import com.example.pathfinder.model.binding.UserRegisterBindingModel;
import com.example.pathfinder.model.enums.Level;
import com.example.pathfinder.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void register(UserRegisterBindingModel userRegisterBindingModel){

        User user = modelMapper.map(userRegisterBindingModel, User.class);
        user.setLevel(Level.BEGINNER);
       this.userRepository.saveAndFlush(user);
    }

    public UserRegisterBindingModel findByEmail(String email){
        return modelMapper.map(this.userRepository.findByEmail(email), UserRegisterBindingModel.class);
    }

    public UserRegisterBindingModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        if (user == null){
            return null;
        }
        return modelMapper.map(user, UserRegisterBindingModel.class);
    }
}

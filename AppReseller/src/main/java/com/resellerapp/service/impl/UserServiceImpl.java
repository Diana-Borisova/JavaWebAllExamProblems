package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.service.UserServiceModel;
import com.resellerapp.model.view.OfferViewModel;
import com.resellerapp.model.view.UserViewModel;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    public UserServiceModel findByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username).orElse(new User()), UserServiceModel.class);
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return this.modelMapper.map(this.userRepository.findByUsernameAndPassword(username,password), UserServiceModel.class);
    }

    @Override
    public String getUsername() {
        User user = userRepository.findById(loggedUser.getId()).orElseThrow();
        return user.getUsername();
    }

    @Override
    public UserServiceModel findByIdNot(Long id) {
        return this.modelMapper.map(this.userRepository.findByIdNot(id).orElse(new User()), UserServiceModel.class);
    }

    @Override
    public LoggedUser findById(Long id) {
        return loggedUser;
    }




}
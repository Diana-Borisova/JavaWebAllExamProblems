package com.softuni.andrey.s.service.impl;

import com.softuni.andrey.s.entity.User;
import com.softuni.andrey.s.entity.services.UserServiceModel;
import com.softuni.andrey.s.repository.UserRepository;
import com.softuni.andrey.s.service.UserService;
import com.softuni.andrey.s.utils.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public UserServiceModel findByEmailAndPassword(String email, String password) {
        return modelMapper.map(this.userRepository.findByEmailAndPassword(email, password), UserServiceModel.class);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = new User();
        user.setUsername(userServiceModel.getUsername());
        user.setEmail(userServiceModel.getEmail());
        user.setPassword(userServiceModel.getPassword());
        user.setBudget(userServiceModel.getBudget());
        user.setConfirmPassword(userServiceModel.getConfirmPassword());
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findByUsernameAndEmail(String username, String email) {
        return modelMapper.map(this.userRepository.findByUsernameAndEmail(username, email), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return modelMapper.map(this.userRepository.findByUsernameAndPassword(username, password), UserServiceModel.class);
    }
}

package com.example.battle_ship.service.impl;

import com.example.battle_ship.model.Dtos.UserLoginDto;
import com.example.battle_ship.model.Dtos.UserRegisterDto;
import com.example.battle_ship.model.entity.User;
import com.example.battle_ship.model.services.UserServiceModel;
import com.example.battle_ship.model.views.UserViewModel;
import com.example.battle_ship.repository.UserRepository;
import com.example.battle_ship.service.UserService;
import com.example.battle_ship.utils.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = modelMapper.map(userRegisterDto, User.class);
        this.userRepository.save(user);

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
       return this.userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    @Override
    public void login(Long id) {
      loggedUser.setId(id);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findByIdNot(Long id) {

        return this.userRepository.findByIdNot(id).stream().collect(Collectors.toList());
    }

    @Override
    public List<UserServiceModel> findAllByIdNot(Long id) {
        List< UserServiceModel> userServiceModel = new ArrayList<>();
         userServiceModel.add(modelMapper.map(this.userRepository.findAllByIdNot(id).orElse(null), UserServiceModel.class));
        return userServiceModel;
    }

}

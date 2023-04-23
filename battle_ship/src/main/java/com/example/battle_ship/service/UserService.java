package com.example.battle_ship.service;


import com.example.battle_ship.model.Dtos.UserLoginDto;
import com.example.battle_ship.model.Dtos.UserRegisterDto;
import com.example.battle_ship.model.entity.User;
import com.example.battle_ship.model.services.UserServiceModel;
import com.example.battle_ship.model.views.UserViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {


    void registerUser(UserRegisterDto userRegisterDto);

    User findByUsernameAndPassword(String username, String password);

    void login(Long id);

    User findById(Long id);

   List<User> findByIdNot(Long id);
   List<UserServiceModel> findAllByIdNot(Long id);


}

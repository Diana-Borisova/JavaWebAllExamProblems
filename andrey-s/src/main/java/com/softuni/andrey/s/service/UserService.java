package com.softuni.andrey.s.service;

import com.softuni.andrey.s.entity.User;
import com.softuni.andrey.s.entity.services.UserServiceModel;

import java.util.Optional;

public interface UserService {

   UserServiceModel findByEmailAndPassword(String email, String password);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndEmail( String username, String email);

    UserServiceModel findByUsernameAndPassword( String username, String password);
}

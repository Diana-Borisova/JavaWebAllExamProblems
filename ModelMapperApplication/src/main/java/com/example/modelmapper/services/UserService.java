package com.example.modelmapper.services;

import com.example.modelmapper.dtos.UserLoginDto;
import com.example.modelmapper.dtos.UserRegisterDto;
import com.example.modelmapper.entities.Game;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);
    void logIn(UserLoginDto userLoginDto);
    void logOut(UserLoginDto userLoginDto);
    Set<String> getGamesByUser();
}

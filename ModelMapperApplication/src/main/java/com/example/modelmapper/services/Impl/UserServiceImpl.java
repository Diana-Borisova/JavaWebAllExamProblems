package com.example.modelmapper.services.Impl;

import com.example.modelmapper.dtos.UserLoginDto;
import com.example.modelmapper.dtos.UserRegisterDto;
import com.example.modelmapper.entities.Game;
import com.example.modelmapper.entities.User;
import com.example.modelmapper.repositories.GameRepository;
import com.example.modelmapper.repositories.UserRepository;
import com.example.modelmapper.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    private User loggedUser;
    private GameRepository gameRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        ModelMapper mapper = new ModelMapper();
        var validationMsg = validate(userRegisterDto);

        if (validationMsg != null) {
            return validationMsg;
        }

        User user = mapper.map(userRegisterDto, User.class);
        if (userRepository.count() == 0) {
            user.setAdmin(true);
        }
        userRepository.save(user);
        return String.format("%s was registered", user.getFullName());
    }


    @Override
    public void logIn(UserLoginDto userLoginDto) {

        User user = userRepository
                .findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (user == null) {
            System.out.println("Incorrect username / password");
        } else {
            userRepository.logIn(userLoginDto);
            loggedUser = user;
            System.out.printf("Successfully logged in %s", user.getFullName());
        }
    }

    public void purchase() {
        Set<Game> games = gameRepository.findAllBy();
        loggedUser.getGames().addAll(games);
    }


    @Override
    public void logOut(UserLoginDto userLoginDto) {
        if (loggedUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            userRepository.logOut(userLoginDto);
            System.out.printf("User %s successfully logged out", loggedUser.getFullName());
        }
    }

    @Override
    public Set<String> getGamesByUser() {
        if (loggedUser != null) {
            purchase();
            return loggedUser.getGames().stream().map(g -> g.getTitle()).collect(Collectors.toSet());
        }
        return null;
    }

    private String validate(UserRegisterDto userRegisterDto) {

        if (!userRegisterDto.getEmail().contains("@") || !userRegisterDto.getEmail().contains(".")) {
            return "Incorrect email.";
        }
        if (userRegisterDto.getPassword().length() < 6 || !userRegisterDto.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            return "Incorrect username / password";
        }
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return "Passwords must match.";
        }
        return null;
    }
}

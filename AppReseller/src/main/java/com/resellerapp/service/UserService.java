package com.resellerapp.service;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.service.UserServiceModel;
import com.resellerapp.model.view.OfferViewModel;
import com.resellerapp.model.view.UserViewModel;
import com.resellerapp.util.LoggedUser;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserServiceModel findByUsername(String username);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    String getUsername();

    UserServiceModel findByIdNot(Long id);

    LoggedUser findById(Long id);


}

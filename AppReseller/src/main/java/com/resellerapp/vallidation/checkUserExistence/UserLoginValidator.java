package com.resellerapp.vallidation.checkUserExistence;

import com.resellerapp.model.binding.UserLoginBindingModel;
import com.resellerapp.model.service.UserServiceModel;
import com.resellerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserLoginValidator implements ConstraintValidator<CheckUserExistence, UserLoginBindingModel> {

    private final UserService userService;

    @Autowired
    public UserLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(CheckUserExistence constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginBindingModel userLoginBindingModel, ConstraintValidatorContext constraintValidatorContext) {
        UserServiceModel user = this.userService.findByUsername(userLoginBindingModel.getUsername());
        return user.getId() != null && user.getPassword().equals(userLoginBindingModel.getPassword());
    }
}

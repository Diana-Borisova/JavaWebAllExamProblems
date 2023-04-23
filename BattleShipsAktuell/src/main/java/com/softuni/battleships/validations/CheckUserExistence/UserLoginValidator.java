package com.softuni.battleships.validations.CheckUserExistence;

import com.softuni.battleships.domain.model.UserLoginModel;
import com.softuni.battleships.domain.model.UserModel;
import com.softuni.battleships.domain.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLoginValidator implements ConstraintValidator<CheckUserExistence, UserLoginModel> {

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
    public boolean isValid(UserLoginModel userLoginModel, ConstraintValidatorContext constraintValidatorContext) {
        UserModel user = this.userService.findByUsername(userLoginModel.getUsername());
        return user.getId() != null && user.getPassword().equals(userLoginModel.getPassword());
    }
}

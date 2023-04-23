package com.example.battle_ship.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Override
    public void initialize(UniqueUsername username) {
    }

    @Override
    public boolean isValid(String username,
                           ConstraintValidatorContext cxt) {
        return username == null;
    }

}
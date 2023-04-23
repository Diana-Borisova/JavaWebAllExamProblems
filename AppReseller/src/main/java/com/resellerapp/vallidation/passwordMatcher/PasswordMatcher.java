package com.resellerapp.vallidation.passwordMatcher;


import com.resellerapp.model.binding.UserRegisterBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterBindingModel> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterBindingModel userRegisterBindingModel, ConstraintValidatorContext constraintValidatorContext) {
        if(userRegisterBindingModel.getPassword() != null && userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            return true;
        }
        constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                .addPropertyNode(userRegisterBindingModel.getConfirmPassword())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}

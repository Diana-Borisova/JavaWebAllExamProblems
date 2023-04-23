package com.softuni.battleships.validations.CheckShipExistence;

import com.softuni.battleships.domain.repositories.ShipRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ShipExistenceValidator implements ConstraintValidator<CheckShipExistence, String> {

    private final ShipRepository shipRepository;

    @Autowired
    public ShipExistenceValidator(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public void initialize(CheckShipExistence constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return this.shipRepository.findByName(name).isEmpty();
    }
}

package com.softuni.battleships.validations.CheckShipExistence;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ShipExistenceValidator.class)
public @interface CheckShipExistence {

    String message() default "Ship already added.";

    Class <?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

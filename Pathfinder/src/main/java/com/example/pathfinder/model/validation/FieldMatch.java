package com.example.pathfinder.model.validation;

import com.example.pathfinder.model.validation.UsernameValidator.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

    String first();
    String second();

    String message () default "Password miss match";

    Class <?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

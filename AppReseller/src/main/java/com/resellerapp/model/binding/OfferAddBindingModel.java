package com.resellerapp.model.binding;


import com.resellerapp.model.entity.ConditionEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferAddBindingModel {
    @NotNull()
    @Size(min = 2, max = 50, message = "Username length must be between 3 and 20 characters (inclusive of 3 and 50).")
    private String description;


    @NotNull
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;

    @NotNull(message = "You must select condition!")
    @Enumerated(EnumType.STRING)
    private ConditionEnum conditionEnum;

}

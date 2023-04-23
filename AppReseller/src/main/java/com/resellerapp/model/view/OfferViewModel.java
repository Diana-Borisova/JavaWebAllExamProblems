package com.resellerapp.model.view;

import com.resellerapp.model.entity.ConditionEnum;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.service.UserServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OfferViewModel {

    private Long id;

    private String description;


    private BigDecimal price;

    private ConditionEnum conditionEnum;
    private User user;



}

package com.resellerapp.model.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionEnum;
import com.resellerapp.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OfferServiceModel {

    private Long id;

    private String description;


    private BigDecimal price;

    private ConditionEnum conditionEnum;
    private User user;



}

package com.resellerapp.model.view;

import com.resellerapp.model.entity.Offer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserViewModel {


    private String username;

    private Set<Offer> boughtOffers;

}


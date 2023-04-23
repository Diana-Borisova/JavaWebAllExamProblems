package com.resellerapp.model.service;

import com.resellerapp.model.entity.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserServiceModel {

    private Long id;
    private String username;

    private String password;

    private String email;

    private String confirmPassword;

    private Offer offer;

    private Set<Offer> boughtOffers;

    public UserServiceModel() {
        this.boughtOffers = new HashSet<>();
    }

    public void buyOffer(Offer boughtOffers) {

        this.boughtOffers.add(boughtOffers);
    }
}


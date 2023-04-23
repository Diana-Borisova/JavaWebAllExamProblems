package com.resellerapp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{


    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;



    @ManyToOne
    private Offer offer;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Offer> boughtOffers;
    public User() {
        this.boughtOffers = new HashSet<>();
    }


    public void buyOffer(Offer boughtOffers) {

        this.boughtOffers.add(boughtOffers);
    }
    public Set<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public User setBoughtOffers(Set<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }


}
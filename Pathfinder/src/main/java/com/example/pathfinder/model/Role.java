package com.example.pathfinder.model;

import com.example.pathfinder.model.enums.UserRoles;

import javax.persistence.*;
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoles name;

    public Long getId() {
        return id;
    }

    public Role setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoles getName() {
        return name;
    }

    public Role setName(UserRoles name) {
        this.name = name;
        return this;
    }

    public Role() {
    }
}

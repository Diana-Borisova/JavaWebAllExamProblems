package com.example.pathfinder.model;

import com.example.pathfinder.model.enums.RouteCategory;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RouteCategory name;

    @Column(columnDefinition = "TEXT")
    private String description;

}

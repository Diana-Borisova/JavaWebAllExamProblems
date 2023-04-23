package com.example.pathfinder.model;

import com.example.pathfinder.model.enums.Level;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    private String name;

    @ManyToOne
    private User author;

    private String videoUrl;

    @Column(columnDefinition = "TEXT")
    private String description;


    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private Set<Picture> pictures;

    @OneToMany(targetEntity = Comment.class, mappedBy = "route", cascade =  CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToMany
    private Set<Category> categories;

    public Route() {
        this.pictures = new HashSet<>();
        this.comments = new HashSet<>();
        this.categories = new HashSet<>();
    }
    public String getDescription() {
        return description;
    }

    public Route setDescription(String description) {
        this.description = description;
        return this;
    }


    public Route setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }



    public Route setComments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Set<Picture> getPictures() {
        return this.pictures;
    }
    public Set<Comment> getComments() {
        return this.comments;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public Route setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }


    public Long getId() {
        return id;
    }

    public Route setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public Route setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public Route setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public Route setName(String name) {
        this.name = name;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Route setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Route setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}

package com.example.modelmapper.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class    Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(name = "trailer_id")
    private String trailerIdent;
    @Column(name = "image_thumbnail")
    private String imageThumbnail;
    @Column(nullable = false)
    private float size;
    @Column(nullable = false)
    private BigDecimal price;
    @Column
    private String description;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(String title, String trailerId,
                String imageThumbnail,
                float size, BigDecimal price,
                String description,
                LocalDate releaseDate) {
        this.title = title;
        this.trailerIdent = trailerId;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerIdent() {
        return trailerIdent;
    }

    public void setTrailerIdent(String trailerId) {
        this.trailerIdent = trailerId;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}

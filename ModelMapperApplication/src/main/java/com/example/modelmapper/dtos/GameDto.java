package com.example.modelmapper.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameDto {
    private String title;
    private BigDecimal price;
    private float size;
    private String trailerIdent;
    private String imageThumbnail;
    private String description;
    private LocalDate releaseDate;

    public GameDto() {
    }

    public GameDto(String title, String trailerId, String imageThumbnail,
                   float size,
                   BigDecimal price,
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

    public GameDto(BigDecimal price, float size) {
        this.price = price;
        this.size = size;
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

    public void setTrailerIdent(String trailerIdent) {
        this.trailerIdent = trailerIdent;
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

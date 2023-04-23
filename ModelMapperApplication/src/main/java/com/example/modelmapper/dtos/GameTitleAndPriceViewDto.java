package com.example.modelmapper.dtos;

import java.math.BigDecimal;

public class GameTitleAndPriceViewDto {

    private String title;


    private BigDecimal price;

    public GameTitleAndPriceViewDto() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
}
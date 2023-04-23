package com.example.battle_ship.utils;


public class LoggedUser {
    private Long id;

    public LoggedUser() {
    }

    public boolean isEmpty(){
        return this.id == null;
    }

    public void clearUser(){
        this.id = null;
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }
}

package com.forgamers.mobile.accelerator.game;

public class Game {
    private String gameName;
    private String countryName;
    private Integer imageid;

    public Game(String gameName, String countryName, Integer imageid) {
        this.gameName = gameName;
        this.countryName = countryName;
        this.imageid = imageid;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    @Override
    public String toString() {
        return gameName;
    }
}

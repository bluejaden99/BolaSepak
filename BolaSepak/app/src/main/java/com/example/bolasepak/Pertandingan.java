package com.example.bolasepak;

import android.graphics.drawable.Drawable;

public class Pertandingan {
    private String nameTeamA;
    private String nameTeamB;
    private String location;
    private String date;
    private String time;
    private Drawable imgTeamA;
    private Drawable imgTeamB;

    public String getCuaca() {
        return cuaca;
    }

    public void setCuaca(String cuaca) {
        this.cuaca = cuaca;
    }

    private String cuaca;

    public String getNameTeamA() {
        return nameTeamA;
    }

    public void setNameTeamA(String nameTeamA) {
        this.nameTeamA = nameTeamA;
    }

    public String getNameTeamB() {
        return nameTeamB;
    }

    public void setNameTeamB(String nameTeamB) {
        this.nameTeamB = nameTeamB;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Drawable getImgTeamA() {
        return imgTeamA;
    }

    public void setImgTeamA(Drawable imgTeamA) {
        this.imgTeamA = imgTeamA;
    }

    public Drawable getImgTeamB() {
        return imgTeamB;
    }

    public void setImgTeamB(Drawable imgTeamB) {
        this.imgTeamB = imgTeamB;
    }

    public Pertandingan(String nameTeamA, String nameTeamB, String location, String date, String time, Drawable imgTeamA, Drawable imgTeamB, String cuaca) {
        this.nameTeamA = nameTeamA;
        this.nameTeamB = nameTeamB;
        this.location = location;
        this.date = date;
        this.time = time;
        this.imgTeamA = imgTeamA;
        this.imgTeamB = imgTeamB;
        this.cuaca = cuaca;
    }
}

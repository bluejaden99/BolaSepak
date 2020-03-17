package com.example.bolasepak;


import android.graphics.drawable.Drawable;

public class EventDetail {
    private String idEvent;
    private String teamHome;
    private String teamAway;
    private String scoreHome;
    private String scoreAway;
    private String matchDate;
    private String matchTime;
    private String matchLocation;
    private String matchWeather;
    private String goalHome;
    private String goalAway;
    private Drawable logoHome;
    private Drawable logoAway;
    private String idTeamHome;
    private String idTeamAway;

    public EventDetail(){};
    public EventDetail(String idEvent, String teamHome, String teamAway, String scoreHome, String scoreAway,
                       String matchDate, String matchTime, String matchLocation, String matchWeather, String goalHome,
                       String goalAway, Drawable logoHome, Drawable logoAway, String idTeamHome, String idTeamAway) {
        this.idEvent = idEvent;
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.matchLocation = matchLocation;
        this.matchWeather = matchWeather;
        this.goalHome = goalHome;
        this.goalAway = goalAway;
        this.logoHome = logoHome;
        this.logoAway = logoAway;
        this.idTeamHome = idTeamHome;
        this.idTeamAway = idTeamAway;

    }

    public String getIdEvent(){
        return this.idEvent;
    }

    public String getTeamHome(){
        return this.teamHome;
    }

    public String getTeamAway(){
        return this.teamAway;
    }

    public String getScoreHome(){
        return this.scoreHome;
    }

    public String getScoreAway(){
        return this.scoreAway;
    }

    public String getMatchDate() {
        return this.matchDate;
    }

    public String getMatchLocation() {
        return this.matchLocation;
    }

    public String getMatchTime() {
        return this.matchTime;
    }

    public String getMatchWeather() {
        return this.matchWeather;
    }
    public String getGoalHome(){return this.goalHome;}
    public String getGoalAway() {
        return this.goalAway;
    }

    public void setGoalAway(String goalAway) {
        this.goalAway = goalAway;
    }

    public void setGoalHome(String goalHome) {
        this.goalHome = goalHome;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public void setMatchLocation(String matchLocation) {
        this.matchLocation = matchLocation;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public void setMatchWeather(String matchWeather) {
        this.matchWeather = matchWeather;
    }

    public void setScoreAway(String scoreAway) {
        this.scoreAway = scoreAway;
    }

    public void setScoreHome(String scoreHome) {
        this.scoreHome = scoreHome;
    }

    public void setTeamAway(String teamAway) {
        this.teamAway = teamAway;
    }

    public void setTeamHome(String teamHome) {
        this.teamHome = teamHome;
    }

    public Drawable getLogoAway() {
        return this.logoAway;
    }

    public Drawable getLogoHome() {
        return this.logoHome;
    }

    public void setLogoAway(Drawable logoAway) {
        this.logoAway = logoAway;
    }

    public void setLogoHome(Drawable logoHome) {
        this.logoHome = logoHome;
    }

    public String getIdTeamAway() {
        return this.idTeamAway;
    }

    public String getIdTeamHome() {
        return this.idTeamHome;
    }

    public void setIdTeamAway(String idTeamAway) {
        this.idTeamAway = idTeamAway;
    }

    public void setIdTeamHome(String idTeamHome) {
        this.idTeamHome = idTeamHome;
    }
}
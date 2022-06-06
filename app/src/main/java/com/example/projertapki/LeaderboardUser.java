package com.example.projertapki;

public class LeaderboardUser {
    private String name;
    private String  points;

    public LeaderboardUser() {
    }

    public LeaderboardUser(String name, String points) {
        this.name = name;
        this.points = points;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}

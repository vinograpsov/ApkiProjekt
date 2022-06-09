package com.example.projertapki;

public class LeaderboardUser {
    private String name;
    private Long  points;

    public LeaderboardUser() {
    }

    public LeaderboardUser(String name, Long points) {
        this.name = name;
        this.points = points;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}

package it.uniroma3.siw.football.dto;

import java.time.LocalDateTime;

public class GameDTO {
    private final Long id;
    private final LocalDateTime dateTime;
    private final Long homeTeamId;
    private final String homeTeamName;
    private final Long awayTeamId;
    private final String awayTeamName;
    private final Integer goalsHome;
    private final Integer goalsAway;
    private final String place;
    private final String status;

    // costruttore, getter e setter

    public GameDTO(Long id, LocalDateTime dateTime, Long homeTeamId, String homeTeamName,
            Long awayTeamId, String awayTeamName, Integer goalsHome, Integer goalsAway,
            String place, String status) {
        this.id = id;
        this.dateTime = dateTime;
        this.homeTeamId = homeTeamId;
        this.homeTeamName = homeTeamName;
        this.awayTeamId = awayTeamId;
        this.awayTeamName = awayTeamName;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
        this.place = place;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public Integer getGoalsHome() {
        return goalsHome;
    }

    public Integer getGoalsAway() {
        return goalsAway;
    }

    public String getPlace() {
        return place;
    }

    public String getStatus() {
        return status;
    }
}
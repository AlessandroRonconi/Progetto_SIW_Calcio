package it.uniroma3.siw.football.model;

import java.util.List;

public class RigaClassifica {
    private Long teamId;
    private String teamName;
    private int playedGames;
    private int wins;
    private int draws;
    private int losses;
    private int goalsFor;
    private int goalsAgainst;
    private int goalsDifference;
    private int points;

    public RigaClassifica(Squadra squadra, List<Partita> partiteSquadra) {
        this.teamId = squadra.getId();
        this.teamName = squadra.getName();
        this.playedGames = partiteSquadra.size();

        for (Partita partita : partiteSquadra) {
            boolean isHome = partita.getHomeTeam().getId().equals(squadra.getId());

            int myGoals;
            if (isHome) {
                myGoals = partita.getGoalsHome();
            } else {
                myGoals = partita.getGoalsAway();
            }

            int opponentGoals;
            if (isHome) {
                opponentGoals = partita.getGoalsAway();
            } else {
                opponentGoals = partita.getGoalsHome();
            }

            this.goalsFor += myGoals;
            this.goalsAgainst += opponentGoals;

            if (myGoals > opponentGoals) {
                this.wins++;
            } else if (myGoals == opponentGoals) {
                this.draws++;
            } else {
                this.losses++;
            }
        }

        this.goalsDifference = this.goalsFor - this.goalsAgainst;
        this.points = (this.wins * 3) + this.draws;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalsDifference() {
        return goalsDifference;
    }

    public void setGoalsDifference(int goalsDifference) {
        this.goalsDifference = goalsDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

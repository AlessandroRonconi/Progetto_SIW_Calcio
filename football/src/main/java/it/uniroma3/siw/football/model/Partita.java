package it.uniroma3.siw.football.model;

import java.time.LocalDateTime;
import java.util.List;

import it.uniroma3.siw.football.model.enums.PartitaStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Partita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;
    @Column(nullable = false)
    private String place;
    @Column(name = "goals_home")
    private Integer goalsHome;
    @Column(name = "goals_away")
    private Integer goalsAway;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PartitaStatus status;

    @ManyToOne
    private Torneo torneo;
    @ManyToOne
    private Squadra homeTeam;
    @ManyToOne
    private Squadra awayTeam;
    @ManyToOne
    private Arbitro arbitro;
    @OneToMany(mappedBy = "partita")
    private List<Commento> commenti;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(Integer goalsHome) {
        this.goalsHome = goalsHome;
    }

    public Integer getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(Integer goalsAway) {
        this.goalsAway = goalsAway;
    }

    public PartitaStatus getStatus() {
        return status;
    }

    public void setStatus(PartitaStatus status) {
        this.status = status;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Squadra getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Squadra homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Squadra getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Squadra awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }

}

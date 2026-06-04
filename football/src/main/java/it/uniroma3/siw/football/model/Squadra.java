package it.uniroma3.siw.football.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "year_of_foundation", nullable = false)
    private Long yearOfFoundation;
    @Column(nullable = false)
    private String city;

    @ManyToMany
    private List<Torneo> tornei;
    @OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL)
    private List<Giocatore> giocatori;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getYearOfFoundation() {
        return yearOfFoundation;
    }

    public void setYearOfFoundation(Long yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Torneo> getTornei() {
        return tornei;
    }

    public void setTornei(List<Torneo> tornei) {
        this.tornei = tornei;
    }

    public List<Giocatore> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(List<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }
}

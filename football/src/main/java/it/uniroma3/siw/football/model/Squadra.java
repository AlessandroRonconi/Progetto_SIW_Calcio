package it.uniroma3.siw.football.model;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "squadre")
    private List<Torneo> tornei;

    @OneToMany(mappedBy = "squadra")
    private List<Giocatore> giocatori;

    // getter e setter invariati...

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

    public void addGiocatore(Giocatore giocatore) {
        if (this.giocatori == null) {
            this.giocatori = new ArrayList<>();
        }
        this.giocatori.add(giocatore);
        giocatore.setSquadra(this);
    }

    public void removeGiocatore(Giocatore giocatore) {
        if (this.giocatori != null) {
            this.giocatori.remove(giocatore);
            giocatore.setSquadra(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Squadra))
            return false;
        Squadra other = (Squadra) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
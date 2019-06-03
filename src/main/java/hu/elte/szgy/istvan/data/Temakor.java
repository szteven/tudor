package hu.elte.szgy.istvan.data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="temakor")
public class Temakor implements Serializable {

    public Temakor(String nev) {
        this.nev = nev;
    }

    public Temakor() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nev;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Tudakozas> tudakozasok = new HashSet<>();

    public Set<Tudakozas> getTudakozasok() {
        return tudakozasok;
    }

    public void setTudakozasok(Set<Tudakozas> tudakozasok) {
        this.tudakozasok = tudakozasok;
    }
}

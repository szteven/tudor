package hu.elte.szgy.istvan.data;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="felhasznalo")
public class Felhasznalo implements Serializable {

    public Felhasznalo(String nev, Date szuldatum, String bankszamla, String felhasznalonev, String email, String jelszo, Tipus tipus) {
        this.nev = nev;
        this.szuldatum = szuldatum;
        this.bankszamla = bankszamla;
        this.felhasznalonev = felhasznalonev;
        this.email = email;
        this.jelszo = jelszo;
        this.tipus = tipus;
    }

    public Felhasznalo() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nev;

    private Date szuldatum;

    private String bankszamla;

    private String felhasznalonev;

    private String email;

    public List<Tudakozas> getUgyfelTudakozasok() {
        return ugyfelTudakozasok;
    }

    public void setUgyfelTudakozasok(List<Tudakozas> ugyfelTudakozasok) {
        this.ugyfelTudakozasok = ugyfelTudakozasok;
    }

    public List<Tudakozas> getTudorTudakozasok() {
        return tudorTudakozasok;
    }

    public void setTudorTudakozasok(List<Tudakozas> tudorTudakozasok) {
        this.tudorTudakozasok = tudorTudakozasok;
    }

    private String jelszo;

    public enum Tipus{ ADMIN, TUDOR, UGYFEL }

    @Enumerated(EnumType.ORDINAL)
    private Tipus tipus;

    public List<Temakor> getKompetenciak() {
        return kompetenciak;
    }

    public void setKompetenciak(List<Temakor> kompetenciak) {
        this.kompetenciak = kompetenciak;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Temakor.class)
    @JoinTable(
            name="kompetencia",
            joinColumns=@JoinColumn(name="felhasznalo_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="temakor_id", referencedColumnName="id"))
    private List<Temakor> kompetenciak = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Tudakozas> ugyfelTudakozasok = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Tudakozas> tudorTudakozasok = new ArrayList<>();

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

    public Date getSzuldatum() {
        return szuldatum;
    }

    public void setSzuldatum(Date szuldatum) {
        this.szuldatum = szuldatum;
    }

    public String getBankszamla() {
        return bankszamla;
    }

    public void setBankszamla(String bankszamla) {
        this.bankszamla = bankszamla;
    }

    public String getFelhasznalonev() {
        return felhasznalonev;
    }

    public void setFelhasznalonev(String felhasznalonev) {
        this.felhasznalonev = felhasznalonev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public Tipus getTipus() {
        return tipus;
    }

    public void setTipus(Tipus tipus) {
        this.tipus = tipus;
    }
}

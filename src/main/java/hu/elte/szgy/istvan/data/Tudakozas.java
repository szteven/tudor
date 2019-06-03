package hu.elte.szgy.istvan.data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tudakozas")
public class Tudakozas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String kerdes;
    private Date kerdesDatum;

    private String valasz;
    private Date valaszDatum;

    private int prioritas;

    private int ertekeles;

    private boolean megvalaszolt;

    private boolean ugyfelLatja;

    private boolean tudorLatja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKerdes() {
        return kerdes;
    }

    public void setKerdes(String kerdes) {
        this.kerdes = kerdes;
    }

    public Date getKerdesDatum() {
        return kerdesDatum;
    }

    public void setKerdesDatum(Date kerdesDatum) {
        this.kerdesDatum = kerdesDatum;
    }

    public String getValasz() {
        return valasz;
    }

    public void setValasz(String valasz) {
        this.valasz = valasz;
    }

    public Date getValaszDatum() {
        return valaszDatum;
    }

    public void setValaszDatum(Date valaszDatum) {
        this.valaszDatum = valaszDatum;
    }

    public int getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(int prioritas) {
        this.prioritas = prioritas;
    }

    public int getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(int ertekeles) {
        this.ertekeles = ertekeles;
    }

    public boolean isMegvalaszolt() {
        return megvalaszolt;
    }

    public void setMegvalaszolt(boolean megvalaszolt) {
        this.megvalaszolt = megvalaszolt;
    }

    public boolean isUgyfelLatja() {
        return ugyfelLatja;
    }

    public void setUgyfelLatja(boolean ugyfelLatja) {
        this.ugyfelLatja = ugyfelLatja;
    }

    public boolean isTudorLatja() {
        return tudorLatja;
    }

    public void setTudorLatja(boolean tudorLatja) {
        this.tudorLatja = tudorLatja;
    }

    public Temakor getTemakor() {
        return temakor;
    }

    public void setTemakor(Temakor temakor) {
        this.temakor = temakor;
    }

    public Felhasznalo getUgyfel() {
        return ugyfel;
    }

    public void setUgyfel(Felhasznalo ugyfel) {
        this.ugyfel = ugyfel;
    }

    public Felhasznalo getTudor() {
        return tudor;
    }

    public void setTudor(Felhasznalo tudor) {
        this.tudor = tudor;
    }

    @ManyToOne
    @JsonIgnore
    private Temakor temakor;

    @ManyToOne
    @JsonIgnore
    private Felhasznalo ugyfel;

    @ManyToOne
    @JsonIgnore
    private Felhasznalo tudor;

}

package com.example.wpa_alpha.PersistenceModels;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Megye {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nev")
    private String nev;
    @Basic
    @Column(name = "regio")
    private String regio;

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

    public String getRegio() {
        return regio;
    }

    public void setRegio(String regio) {
        this.regio = regio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Megye megye = (Megye) o;
        return id == megye.id && Objects.equals(nev, megye.nev) && Objects.equals(regio, megye.regio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev, regio);
    }

    @Override
    public String toString() {
        return "Megye{" +
                "id=" + id +
                ", nev='" + nev + '\'' +
                ", regio='" + regio + '\'' +
                '}';
    }
}

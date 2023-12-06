package com.example.wpa_alpha.PersistenceModels;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Helyszin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nev")
    private String nev;
    @Basic
    @Column(name = "megyeid")
    private int megyeid;
    @OneToOne
    @JoinColumn(name = "megyeid", referencedColumnName = "id", insertable = false, updatable = false)
    private Megye megye;

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

    public int getMegyeid() {
        return megyeid;
    }

    public void setMegyeid(int megyeid) {
        this.megyeid = megyeid;
    }

    public Megye getMegye() {
        return megye;
    }

    public void setMegye(Megye megye) {
        this.megye = megye;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Helyszin helyszin = (Helyszin) o;
        return id == helyszin.id && megyeid == helyszin.megyeid && Objects.equals(nev, helyszin.nev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev, megyeid);
    }
}

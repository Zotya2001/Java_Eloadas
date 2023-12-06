package com.example.wpa_alpha.Modells.Stream;

public class Helyszin {
    private int id;
    private String nev;
    private int megyeid;

    public Helyszin(int id, String nev, int megyeid) {
        this.id = id;
        this.nev = nev;
        this.megyeid = megyeid;
    }

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

    @Override
    public String toString() {
        return "Helyszin{" +
                "id=" + id +
                ", nev='" + nev + '\'' +
                ", megyeid=" + megyeid +
                '}';
    }
}

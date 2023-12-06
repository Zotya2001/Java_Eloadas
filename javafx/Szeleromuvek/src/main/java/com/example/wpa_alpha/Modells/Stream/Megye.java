package com.example.wpa_alpha.Modells.Stream;

public class Megye {
    private int id;
    private String nev;
    private String regio;

    public Megye(int id, String nev, String regio) {
        this.id = id;
        this.nev = nev;
        this.regio = regio;
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

    public String getRegio() {
        return regio;
    }

    public void setRegio(String regio) {
        this.regio = regio;
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

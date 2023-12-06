package com.example.wpa_alpha.Modells.Stream;

public class Torony {
    private int id;
    private int darab;
    private int teljesítmény;
    private int kezdev;

    @Override
    public String toString() {
        return "Torony{" +
                "id=" + id +
                ", darab=" + darab +
                ", teljesítmény=" + teljesítmény +
                ", kezdev=" + kezdev +
                ", helyszinId=" + helyszinId +
                '}';
    }

    private int helyszinId;

    public Torony(int id, int darab, int teljesítmény, int kezdev, int helyszinId) {
        this.id = id;
        this.darab = darab;
        this.teljesítmény = teljesítmény;
        this.kezdev = kezdev;
        this.helyszinId = helyszinId;
    }

    public int getId() {
        return id;
    }

    public int getDarab() {
        return darab;
    }

    public int getTeljesítmény() {
        return teljesítmény;
    }

    public int getKezdev() {
        return kezdev;
    }

    public int getHelyszinId() {
        return helyszinId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDarab(int darab) {
        this.darab = darab;
    }

    public void setTeljesítmény(int teljesítmény) {
        this.teljesítmény = teljesítmény;
    }

    public void setKezdev(int kezdev) {
        this.kezdev = kezdev;
    }

    public void setHelyszinId(int helyszinId) {
        this.helyszinId = helyszinId;
    }

}

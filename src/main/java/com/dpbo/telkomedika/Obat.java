package com.dpbo.telkomedika;

public class Obat {
    private String idObat;
    private String nama;
    private int dosis;

    public Obat(String idObat, String nama, int dosis) {
        this.idObat = idObat;
        this.nama = nama;
        this.dosis = dosis;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public String getInfoObat() {
        return "Obat: " + nama + ", Dosis: " + dosis + " mg";
    }
}

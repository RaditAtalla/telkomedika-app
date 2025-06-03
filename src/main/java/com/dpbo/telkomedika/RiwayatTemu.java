package com.dpbo.telkomedika;


public class RiwayatTemu {
    private String idTemu;
    private String tanggal;
    private String diagnosis;
  
    public RiwayatTemu(String idTemu, String tanggal, String diagnosis) {
        this.idTemu = idTemu;
        this.tanggal = tanggal;
        this.diagnosis = diagnosis;
    }

    public String getIdTemu() {
        return idTemu;
    }

    public void setIdTemu(String idTemu) {
        this.idTemu = idTemu;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    public void lihatRiwayat() {
        System.out.println("ID Temu: " + idTemu);
        System.out.println("Tanggal: " + tanggal);
        System.out.println("Diagnosis: " + diagnosis);
    
    }
}

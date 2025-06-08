package com.dpbo.telkomedika;

public abstract class Dokter extends User {
  private String kodeDokter;
  private boolean[] jadwalDokter = new boolean[7];

  public Dokter(String nama, String email, String password, String kodeDokter, boolean[] jadwalDokter) {
    super(nama, email, password);
    this.kodeDokter = kodeDokter;
    this.jadwalDokter = jadwalDokter;
  }

  public String getKodeDokter() {
    return this.kodeDokter;
  }

  public boolean[] getJadwalDokter() {
    return this.jadwalDokter;
  }
}

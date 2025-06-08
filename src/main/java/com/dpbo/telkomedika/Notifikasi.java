package com.dpbo.telkomedika;

public class Notifikasi {
  private String judul;
  private String isi;
  
  public Notifikasi(String judul, String isi) {
    this.judul = judul;
    this.isi = isi;
  }

  public String getJudul() {
    return this.judul;
  }

  public String getIsi() {
    return this.isi;
  }
}

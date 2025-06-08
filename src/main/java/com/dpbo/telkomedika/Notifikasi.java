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

  @Override
  public String toString() {
    return "Judul: " + judul + "\nIsi: " + isi + "\n";
  }

  public static void showAllNotifications() {
    System.out.println("===== Notifikasi =====");
    if (App.notifications.size() > 0) {
      for (Notifikasi notifikasi : App.notifications) {
        System.out.println(notifikasi);
      }
    } else {
      System.out.println("@ Tidak ada notifikasi");
    }
  }
}

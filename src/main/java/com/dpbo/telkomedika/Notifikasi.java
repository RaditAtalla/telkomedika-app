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
    int i = 0;
    if (App.notifications.size() > 0) {
      for (Notifikasi notifikasi : App.notifications) {
        System.out.println(i + ". " + notifikasi);
      }
    } else {
      System.out.println("@ Tidak ada notifikasi");
    }
  }

  public static void sendNotification() {
    System.out.println("===== Kirim Notifikasi =====");
    System.out.println(">> Judul: ");
    String judul = App.input.nextLine();
    System.out.println(">> Isi: ");
    String isi = App.input.nextLine();

    Notifikasi notifikasi = new Notifikasi(judul, isi);
    App.notifications.add(notifikasi);
    System.out.println("@ Notifikasi berhasil dikirim");
  }
}

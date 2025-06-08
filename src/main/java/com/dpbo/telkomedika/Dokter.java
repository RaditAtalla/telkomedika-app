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

  public static void showDokterPage() {
    int menu = -1;

    System.out.println("===== Dokter Home =====");
    do {
      Dokter.showDokterMenu();
      System.out.println("Pilih menu:");

      try {
        menu = Integer.parseInt(App.input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("@ Harap hanya memasukkan angka");
      }

      if (menu == 1) {
        Pendaftaran.getPendaftaranBasedOnDokter();
      } else if (menu == 2) {
        System.out.println("Buat laporan");
      } else if (menu == 0) {
        System.out.println("@ Log out");
      } else {
        System.out.println("@ Harap pilih antara menu 0 - 6");
      }
    } while (menu != 0);
  }

  public static void showDokterMenu() {
    System.out.println("===== MENU =====");
    System.out.println("1. Lihat jadwal temu");
    System.out.println("2. Buat laporan pemeriksaan");
    System.out.println("0. Log out");
    System.out.println("==========");
  }
}

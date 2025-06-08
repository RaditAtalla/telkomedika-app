package com.dpbo.telkomedika;

import java.util.ArrayList;

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

  public static void buatLaporan(ArrayList<Pendaftaran> pendaftaran) {
    System.out.println(">> Buat laporan untuk: ");
    int laporanKe = 0;
    try {
      laporanKe = Integer.parseInt(App.input.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("@ Harap hanya memasukkan angka");
    }

    if (laporanKe <= 0 || laporanKe > pendaftaran.size()) {
      System.out.println("@ Harap pilih antara 1 - " + pendaftaran.size());
    }

    System.out.println(">> Laporan: ");
    String laporan = App.input.nextLine();
    System.out.println(">> Tindakan: ");
    String tindakan = App.input.nextLine();
    System.out.println(">> Obat: ");
    String namaObat = App.input.nextLine();

    Obat obat = Obat.getObat(namaObat);
    if (obat == null) {
      System.out.println("@ Obat tidak ditemukan");
      return;
    }

    RiwayatTemu riwayatTemu = new RiwayatTemu(pendaftaran.get(laporanKe - 1), laporan, tindakan, obat);

    for (User user : App.users) {
      if (user.getEmail().equals(riwayatTemu.getPendaftaran().getPasien().getEmail())) {
        ArrayList<RiwayatTemu> prevRiwayatTemu = ((Pasien) user).getRiwayatTemu();
        prevRiwayatTemu.add(riwayatTemu);
        ((Pasien) user).setRiwayatTemu(prevRiwayatTemu);
        System.out.println("@ Laporan berhasil dibuat");
      }
    }
  }

  public static void showDokterPage() {
    int menu = -1;

    System.out.println("===== Dokter Home =====");
    do {
      Dokter.showDokterMenu();
      System.out.println(">> Pilih menu:");

      try {
        menu = Integer.parseInt(App.input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("@ Harap hanya memasukkan angka");
      }

      if (menu == 1) {
        System.out.println();
        Pendaftaran.getPendaftaranBasedOnDokter();
      } else if (menu == 0) {
        System.out.println("@ Log out");
      } else {
        System.out.println("@ Harap pilih antara menu 0 - 6");
      }
    } while (menu != 0);
  }

  public static void showDokterMenu() {
    System.out.println("1. Lihat jadwal temu");
    System.out.println("0. Log out");
  }
}

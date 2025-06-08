package com.dpbo.telkomedika;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Pendaftaran {
  private Pasien pasien;
  private Dokter dokter;
  protected LocalDate tanggal;
  private LocalTime waktu;
  private String keluhan;

  public Pendaftaran(Pasien pasien, Dokter dokter, LocalDate tanggal, LocalTime waktu, String keluhan) {
    this.pasien = pasien;
    this.dokter = dokter;
    this.tanggal = tanggal;
    this.waktu = waktu;
    this.keluhan = keluhan;
  }

  public Pasien getPasien() {
    return this.pasien;
  }

  public Dokter getDokter() {
    return this.dokter;
  }

  public LocalDate getTanggal() {
    return this.tanggal;
  }

  public LocalTime getWaktu() {
    return this.waktu;
  }

  public String keluhan() {
    return this.keluhan;
  }

  @Override
  public String toString() {
    return "Pasien: " + this.pasien.getNama() + "\nDokter: " + this.dokter.getNama() + "\nTanggal: " + this.tanggal + "\nJam: " + this.waktu + "\nKeluhan: " + this.keluhan;
  }

  public static void getPendaftaranBasedOnDokter() {
    boolean pendaftaranExist = false;

    System.out.println("===== Jadwal temu anda =====");
    for (Pendaftaran pendaftaran : App.daftarPendaftaran) {
      if (pendaftaran.getDokter().getNama().equals(App.currentUser.getNama())) {
        pendaftaranExist = true;
        System.out.println(pendaftaran);
      }
    }

    if (!pendaftaranExist) {
      System.out.println("@ Tidak ada jadwal temu");
    }
  }

  public static void viewAllPendaftaran() {
    System.out.println("===== Daftar jadwal temu =====");
    if (App.daftarPendaftaran.size() > 0) {
      for (Pendaftaran pendaftaran : App.daftarPendaftaran) {
        System.out.println(pendaftaran);
      }
    } else {
      System.out.println("@ Tidak ada jadwal temu");
    }
  }

  public static void showPendaftaranPage() {
    System.out.println("===== BUAT JADWAL TEMU =====");
    System.out.println("1. Dengan dokter umum");
    System.out.println("2. Dengan dokter gigi");
    System.out.println("0. Kembali");
    System.out.println("==========");

    System.out.println("Input:");
    int menu = -1;
    try {
      menu = Integer.parseInt(App.input.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("@ Harap hanya memasukkan angka");
    }

    if (menu == 1) {
      PendaftaranUmum.handlePendaftaranUmum();
    } else if (menu == 2) {
      PendaftaranGigi.handlePendaftaranGigi();
    } else if (menu == 0) {
      return;
    } else {
      System.out.println("@ Harap pilih antara menu 0 - 2");
    }
  }
}

package com.dpbo.telkomedika;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
    return "Pasien: " + this.pasien.getNama() + " | Dokter: " + this.dokter.getNama() + " | Tanggal: " + this.tanggal
        + " | Jam: " + this.waktu + " | Keluhan: " + this.keluhan;
  }

  public static void getPendaftaranBasedOnDokter() {
    boolean pendaftaranExist = false;

    System.out.println("===== Jadwal temu anda =====");
    int i = 1;
    ArrayList<Pendaftaran> pendaftaranFiltered = new ArrayList<>();
    for (Pendaftaran pendaftaran : App.daftarPendaftaran) {
      if (pendaftaran.getDokter().getNama().equals(App.currentUser.getNama())) {
        pendaftaranExist = true;
        pendaftaranFiltered.add(pendaftaran);
        System.out.println(i + ". " + pendaftaran);
      }

      i++;
    }

    if (!pendaftaranExist) {
      System.out.println("@ Tidak ada jadwal temu");
      return;
    }

    Dokter.buatLaporan(pendaftaranFiltered);
  }

  public static void viewAllPendaftaran() {
    System.out.println("===== Daftar jadwal temu =====");
    int i = 1;
    if (App.daftarPendaftaran.size() > 0) {
      for (Pendaftaran pendaftaran : App.daftarPendaftaran) {
        System.out.println(i + ". " + pendaftaran);
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

    System.out.println(">> Pilih menu:");
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

package com.dpbo.telkomedika;

import java.util.ArrayList;

public class RiwayatTemu {
  private Pendaftaran pendaftaran;
  private String laporan;
  private String tindakan;
  private Obat obat;

  public RiwayatTemu(Pendaftaran pendaftaran, String laporan, String tindakan, Obat obat) {
    this.pendaftaran = pendaftaran;
    this.laporan = laporan;
    this.tindakan = tindakan;
    this.obat = obat;
  }

  public Pendaftaran getPendaftaran() {
    return this.pendaftaran;
  }

  public String getLaporan() {
    return this.laporan;
  }

  public String getTindakan() {
    return this.tindakan;
  }

  public Obat getObat() {
    return this.obat;
  }

  @Override
  public String toString() {
    return "Pertemuan dengan Dr. " + this.pendaftaran.getDokter().getNama() + " (" + this.pendaftaran.getTanggal()
        + ")";
  }

  public static void viewRiwayatTemu() {
    System.out.println("===== Riwayat Temu =====");
    int menu = -1;

    do {
      boolean riwayatExist = false;
      int i = 1;
      ArrayList<RiwayatTemu> daftarRiwayatTemu = new ArrayList<>();
      for (User user : App.users) {
        if (user.getNama().equals(App.currentUser.getNama())) {
          for (RiwayatTemu riwayat : ((Pasien) user).getRiwayatTemu()) {
            riwayatExist = true;
            daftarRiwayatTemu.add(riwayat);
            System.out.println(i + ". " + riwayat);
            i++;
          }
        }
      }

      if (!riwayatExist) {
        System.out.println("@ Tidak ada riwayat temu");
        return;
      }

      System.out.println();
      System.out.println("1. Lihat detail");
      System.out.println("0. Kembali");
      System.out.println(">> Pilih menu:");

      try {
        menu = Integer.parseInt(App.input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("@ Harap hanya memasukkan angka");
      }

      if (menu == 1) {
        int laporanKe = 0;
        System.out.println(">> Lihat detail laporan ke-:");
        try {
          laporanKe = Integer.parseInt(App.input.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("@ Harap hanya memasukkan angka");
        }

        if (laporanKe < 0) {
          System.out.println("Harap tidak memasukkan angka negatif");
        }

        RiwayatTemu detailRiwayatTemu = daftarRiwayatTemu.get(laporanKe - 1);
        System.out.println("===");
        System.out.println("Pasien: " + detailRiwayatTemu.getPendaftaran().getPasien().getNama());
        System.out.println("Dokter: " + detailRiwayatTemu.getPendaftaran().getDokter().getNama());
        System.out.println("Laporan: " + detailRiwayatTemu.getLaporan());
        System.out.println("Tindakan: " + detailRiwayatTemu.getTindakan());
        System.out.println("Obat: " + detailRiwayatTemu.getObat().getNama());
        System.out.println("===");
      } else if (menu == 0) {
        return;
      }

    } while (menu != 0);

  }
}

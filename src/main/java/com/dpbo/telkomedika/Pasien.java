package com.dpbo.telkomedika;

import java.util.ArrayList;

public class Pasien extends User implements ICallAmbulance {
  private String nomorInduk;
  private ArrayList<String> riwayatPenyakit;

  public Pasien(String nama, String email, String password, String nomorInduk) {
    super(nama, email, password);
    this.nomorInduk = nomorInduk;
  }

  public Pasien(String nama, String email, String password, String nomorInduk, ArrayList<String> riwayatPenyakit) {
    super(nama, email, password);
    this.riwayatPenyakit = riwayatPenyakit;
    this.nomorInduk = nomorInduk;
  }

  public ArrayList<String> getRiwayatPenyakit() {
    return this.riwayatPenyakit;
  }

  public void setRiwayatPenyakit(ArrayList<String> riwayatPenyakit) {
    this.riwayatPenyakit = riwayatPenyakit;
  }

  public String getNomorInduk() {
    return this.nomorInduk;
  }

  @Override
  public void callAmbulance() {
    System.out.println("===== Panggil ambulans =====");
    System.out.println("Lokasi penjemputan:");
    String lokasi = App.input.nextLine();

    System.out.println("@ Ambulans segera menuju " + lokasi);
  }

  public static void showPasienPage() {
    int menu = -1;

    System.out.println("===== Pasien Home =====");
    do {
      showPasienMenu();
      System.out.println("Pilih menu: ");

      try {
        menu = Integer.parseInt(App.input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("@ Harap hanya memasukkan angka");
      }

      if (menu == 1) {
        Pendaftaran.showPendaftaranPage();
      } else if (menu == 2) {
        System.out.println("Nomor antrean");
      } else if (menu == 3) {
        System.out.println("Notifikasi");
      } else if (menu == 4) {
        ((Pasien) App.currentUser).callAmbulance();
      } else if (menu == 5) {
        System.out.println("Beri feedback");
      } else if (menu == 6) {
        System.out.println("Update data");
      } else if (menu == 0) {
        System.out.println("@ Log out");
      } else {
        System.out.println("@ Harap pilih antara menu 0 - 6");
      }
    } while (menu != 0);

  }

  public static void showPasienMenu() {
    System.out.println("===== MENU =====");
    System.out.println("1. Buat jadwal temu dengan dokter");
    System.out.println("2. Lihat nomor antrean");
    System.out.println("3. Lihat notifikasi");
    System.out.println("4. Panggil ambulans");
    System.out.println("5. Beri feedback");
    System.out.println("6. Update data");
    System.out.println("0. Log out");
    System.out.println("==========");
  }
}

package com.dpbo.telkomedika;

public class Staff extends User {
  private String kodePegawai;

  public Staff(String nama, String email, String password, String kodePegawai) {
    super(nama, email, password);
    this.kodePegawai = kodePegawai;
  }

  public String getKodePegawai() {
    return this.kodePegawai;
  }

  public static void showStaffPage() {
    int menu = -1;

    System.out.println("===== Staff Home =====");
    do {
      Staff.showStaffMenu();
      System.out.println("Pilih menu:");

      try {
        menu = Integer.parseInt(App.input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("@ Harap hanya memasukkan angka");
      }

      if (menu == 1) {
        Pendaftaran.viewAllPendaftaran();
      } else if (menu == 2) {
        Feedback.viewAllFeedbacks();
      } else if (menu == 3) {
        Notifikasi.sendNotification();
      } else if (menu == 4) {
        System.out.println("Panggil ambulans");
      } else if (menu == 5) {
        System.out.println("Daftar obat");
      } else if (menu == 0) {
        System.out.println("@ Log out");
      } else {
        System.out.println("@ Harap pilih antara menu 0 - 6");
      }

    } while (menu != 0);
  }

  public static void showStaffMenu() {
    System.out.println("===== MENU =====");
    System.out.println("1. Lihat daftar jadwal temu");
    System.out.println("2. Lihat feedback");
    System.out.println("3. Kirim notifikasi ke pasien");
    System.out.println("4. Panggil ambulans rumah sakit");
    System.out.println("5. Lihat inventaris obat");
    System.out.println("0. Log out");
    System.out.println("==========");
  }

}

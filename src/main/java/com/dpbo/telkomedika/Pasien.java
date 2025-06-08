package com.dpbo.telkomedika;

import java.util.ArrayList;

public class Pasien extends User implements ICallAmbulance, IFeedback {
  private String nomorInduk;
  private String nomorAntrean;
  private ArrayList<String> riwayatPenyakit = new ArrayList<>();
  private ArrayList<RiwayatTemu> riwayatTemu = new ArrayList<>();

  public Pasien(String nama, String email, String password, String nomorInduk) {
    super(nama, email, password);
    this.nomorInduk = nomorInduk;
  }

  public Pasien(String nama, String email, String password, String nomorInduk, ArrayList<String> riwayatPenyakit) {
    super(nama, email, password);
    this.riwayatPenyakit = riwayatPenyakit;
    this.nomorInduk = nomorInduk;
  }

  public Pasien(String nama, String email, String password, String nomorInduk, ArrayList<String> riwayatPenyakit,
      ArrayList<RiwayatTemu> riwayatTemu) {
    super(nama, email, password);
    this.riwayatPenyakit = riwayatPenyakit;
    this.nomorInduk = nomorInduk;
    this.riwayatTemu = riwayatTemu;
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

  public ArrayList<RiwayatTemu> getRiwayatTemu() {
    return this.riwayatTemu;
  }

  public void setRiwayatTemu(ArrayList<RiwayatTemu> riwayatTemu) {
    this.riwayatTemu = riwayatTemu;
  }

  public String getNomorAntrean() {
    return this.nomorAntrean;
  }

  public void setNomorAntrean(String nomorAntrean) {
    this.nomorAntrean = nomorAntrean;
  }

  @Override
  public String toString() {
    return super.toString() + "Nomor Induk: " + nomorInduk + "\nRiwayat penyakit: "
        + String.join(", ", this.riwayatPenyakit);
  }

  @Override
  public void callAmbulance() {
    System.out.println("===== Panggil ambulans =====");
    System.out.println(">> Lokasi penjemputan:");
    String lokasi = App.input.nextLine();

    System.out.println("@ Ambulans segera menuju " + lokasi);
  }

  @Override
  public void sendFeedback() {
    System.out.println("===== Kirim feedback =====");
    System.out.println(">> Konten: ");

    String konten = App.input.nextLine();
    Feedback feedback = new Feedback(App.currentUser, konten);
    App.feedbacks.add(feedback);
    System.out.println("@ Feedback berhasil dikirim");
  }

  public static void showPasienPage() {
    int menu = -1;

    System.out.println("===== Pasien Home =====");
    do {
      showPasienMenu();
      System.out.println(">> Pilih menu: ");

      try {
        menu = Integer.parseInt(App.input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("@ Harap hanya memasukkan angka");
      }

      if (menu == 1) {
        System.out.println();
        Pendaftaran.showPendaftaranPage();
      } else if (menu == 2) {
        System.out.println();
        CodeGeneration.viewUserCode();
      } else if (menu == 3) {
        System.out.println();
        Notifikasi.showAllNotifications();
      } else if (menu == 4) {
        System.out.println();
        ((Pasien) App.currentUser).callAmbulance();
      } else if (menu == 5) {
        System.out.println();
        ((Pasien) App.currentUser).sendFeedback();
      } else if (menu == 6) {
        System.out.println();
        System.out.println("===== Profil =====");
        for (User u : App.users) {
          if (u.getEmail().equals(App.currentUser.getEmail())) {
            int editMenu = -1;

            do {
              System.out.println(((Pasien) u).toString());
              System.out.println();

              System.out.println("1. Tambah riwayat penyakit");
              System.out.println("0. Kembali");
              System.out.println(">> Pilih menu:");

              try {
                editMenu = Integer.parseInt(App.input.nextLine());
              } catch (NumberFormatException e) {
                System.out.println("@ Harap hanya memasukkan angka");
              }

              if (editMenu == 1) {
                System.out.println("===== Tambah riwayat penyakit =====");
                System.out.println(">> Penyakit baru:");
                String riwayatPenyakitBaru = App.input.nextLine();
                ArrayList<String> riwayatPenyakit = ((Pasien) u).getRiwayatPenyakit();

                riwayatPenyakit.add(riwayatPenyakitBaru);
                System.out.println("@ Berhasil ditambahkan");
              } else if (editMenu == 0) {
                return;
              } else {
                System.out.println("@ Harap pilih antara menu 0 - 1");
              }
            } while (editMenu != 0);
          }
        }
      } else if (menu == 7) {
        System.out.println();
        RiwayatTemu.viewRiwayatTemu();
      } else if (menu == 0) {
        System.out.println("@ Log out");
      } else {
        System.out.println("@ Harap pilih antara menu 0 - 7");
      }
    } while (menu != 0);

  }

  public static void showPasienMenu() {
    System.out.println("1. Buat jadwal temu dengan dokter");
    System.out.println("2. Lihat nomor antrean");
    System.out.println("3. Lihat notifikasi");
    System.out.println("4. Panggil ambulans");
    System.out.println("5. Beri feedback");
    System.out.println("6. Lihat profil");
    System.out.println("7. Lihat riwayat temu");
    System.out.println("0. Log out");
  }
}

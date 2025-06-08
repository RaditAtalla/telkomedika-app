package com.dpbo.telkomedika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {
  public static Scanner input = new Scanner(System.in);
  public static AppBiz biz = new AppBiz();
  public static ArrayList<User> users = biz.initializeUser();
  public static ArrayList<Feedback> feedbacks = new ArrayList<>();
  public static ArrayList<Notifikasi> notifications = new ArrayList<>();
  public static ArrayList<Pendaftaran> daftarPendaftaran = new ArrayList<>();
  public static ArrayList<Obat> daftarObat = new ArrayList<>();

  public static HashMap<Pendaftaran, String> daftarAntrean = new HashMap<>();

  public static User currentUser = null;

  public static void main(String[] args) {
    int menu = -1;

    do {
      System.out.println("===== Telkomedika App =====");
      System.out.println("1. Login");
      System.out.println("0. Exit");
      System.out.println(">> Pilih menu:");

      try {
        menu = Integer.parseInt(App.input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("@ Harap hanya memasukkan angka");
      }

      if (menu == 1) {
        System.out.println(">> Email:");
        String email = input.nextLine();
        System.out.println(">> Password:");
        String password = input.nextLine();
  
        // TODO: handle kalo login gagal
        currentUser = biz.login(email, password);
        if (currentUser instanceof Pasien) {
          Pasien.showPasienPage();
        } else if (currentUser instanceof Staff) {
          Staff.showStaffPage();
        } else if (currentUser instanceof Dokter) {
          Dokter.showDokterPage();
        }
      } else if (menu == 0) {
        System.out.println("@ Keluar...");
      } else {
        System.out.println("@ Harap pilih antara menu 1 atau 0");
      }

    } while (menu != 0);

    input.close();
  }
}

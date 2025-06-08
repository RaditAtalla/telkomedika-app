package com.dpbo.telkomedika;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
  public static Scanner input = new Scanner(System.in);
  public static UserBiz biz = new UserBiz();
  public static ArrayList<User> users = biz.initializeUser();
  public static User currentUser = null;

  public static void main(String[] args) {

    System.out.println("Email:");
    String email = input.nextLine();
    System.out.println("Password:");
    String password = input.nextLine();

    // TODO: handle kalo login gagal
    currentUser = biz.login(email, password);

    if (currentUser instanceof Pasien) {
      Pasien.showPasienPage();
    } else if (currentUser instanceof Staff) {
      System.out.println("Anda staff");
    } else if (currentUser instanceof Dokter) {
      System.out.println("Anda dokter");
    }

    input.close();
  }
}

package com.dpbo.telkomedika;

import java.util.ArrayList;

public class AppBiz {
  public ArrayList<User> initializeUser() {
    ArrayList<User> users = new ArrayList<>();

    ArrayList<String> riwayatPenyakit = new ArrayList<>();
    riwayatPenyakit.add("Operasi amandel");
    riwayatPenyakit.add("DBD");
  
    users.add(new Pasien("Radit", "radit@mail.com", "12345", "1030001", riwayatPenyakit));
    users.add(new Pasien("Ilham", "ilham@mail.com", "12345", "1030002"));
    users.add(new Staff("Nauval", "nauval@mail.com", "12345", "NVL"));
    users.add(new Staff("Arsal", "arsal@mail.com", "12345", "ARL"));
    users.add(new DokterUmum("Keisya", "keisya@mail.com", "12345", "KSY", new boolean[] {true, true, true, true, true, true, true}));
    users.add(new DokterGigi("Shiky", "shiky@mail.com", "12345", "SKY", new boolean[] {false, true, false, true, false, true, false}));

    return users;
  }

  public User login(String email, String password) {
    boolean emailExist = false;
    boolean passwordCorrect = false;
    User currentUser = null;

    for (User u : this.initializeUser()) {
      if (u.getEmail().equals(email)) {
        emailExist = true;
        if (u.getPassword().equals(password)) {
          passwordCorrect = true;
          currentUser = u;
          break;
        }
      }
    }

    if (!emailExist) {
      System.out.println("@ Email tidak ditemukan");
    } else if (!passwordCorrect) {
      System.out.println("@ Password salah");
    }

    return currentUser;
  }
}

package com.dpbo.telkomedika;

import java.util.Random;

public class CodeGeneration {
  private static final String character = "ABCDEFGHIJKLMOPQRSTUVWXYZ1234567890";
  private static Random random = new Random();

  public static String generateCode() {
    String hasil = "";
    for (int i = 0; i < 6; i++) {
      int index = random.nextInt(character.length());
      hasil += character.charAt(index);
    }

    return hasil;
  }

  public static void viewUserCode() {
    System.out.println("===== Nomor Antrean =====");
    boolean antreanExist = false;
    int i = 1;
    System.out.println("@ Nomor antrean anda: " + ((Pasien) App.currentUser).getNomorAntrean());
    System.out.println("@ Urutan nomor antrean:");
    for (Pendaftaran pendaftaran : App.daftarAntrean.keySet()) {
      antreanExist = true;
      System.out.println(i + ". " + App.daftarAntrean.get(pendaftaran));
    }

    if (!antreanExist) {
      System.out.println("@ Tidak ada antrean");
    }
  }

}

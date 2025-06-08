package com.dpbo.telkomedika;

public class Obat {
  private String nama;
  private int stok;

  public Obat(String nama, int stok) {
    this.nama = nama;
    this.stok = stok;
  }

  public String getNama() {
    return this.nama;
  }

  public int getStok() {
    return this.stok;
  }

  public void setStok(int stok) {
    this.stok = stok;
  }

  @Override
  public String toString() {
    return "Nama: " + this.nama + "\nStok: " + this.stok;
  }

  public static Obat getObat(String nama) {
    for (Obat obat : App.daftarObat) {
      if (obat.getNama().equals(nama)) {
        return obat;
      }
    }

    return null;
  }

  public static void viewAllObat() {
    int menu = -1;

    do {
      System.out.println("===== Obat =====");
      System.out.println("1. Lihat obat");
      System.out.println("2. Tambah obat");
      System.out.println("3. Ambil obat");
      System.out.println("0. Kembali");
      System.out.println(">> Pilih menu: ");

      try {
        menu = Integer.parseInt(App.input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("@ Harap hanya memasukkan angka");
      }

      if (menu == 1) {
        if (App.daftarObat.size() > 0) {
          for (Obat obat : App.daftarObat) {
            if (obat.getStok() == 0) {
              System.out.println("@ Tidak ada obat");
              break;
            }
            System.out.println(obat);
          }
        } else {
          System.out.println("@ Tidak ada obat");
        }
      } else if (menu == 2) {
        System.out.println(">> Nama obat: ");
        String namaObat = App.input.nextLine();

        int stokObat = 0;
        try {
          System.out.println(">> Stok: ");
          stokObat = Integer.parseInt(App.input.nextLine());
        } catch (NumberFormatException e) {
          System.out.println("@ Harap hanya memasukkan angka");
        }

        if (stokObat <= 0) {
          System.out.println("@ Harap masukkan stok lebih dari 0");
          return;
        }

        boolean obatAlreadyExist = false;

        for (Obat obat : App.daftarObat) {
          if (obat.getNama().equals(namaObat)) {
            obatAlreadyExist = true;
            obat.setStok(obat.getStok() + stokObat);
            System.out.println("@ Obat berhasil ditambah");
            break;
          }
        }

        if (!obatAlreadyExist) {
          App.daftarObat.add(new Obat(namaObat, stokObat));
          System.out.println("@ Obat berhasil ditambah");
        }
      } else if (menu == 3) {
        System.out.println(">> Nama obat:");
        String namaObat = App.input.nextLine();

        boolean obatExist = false;

        for (Obat obat : App.daftarObat) {
          if (obat.getNama().equals(namaObat)) {
            obatExist = true;
            int obatDiambil = 0;
            try {
              System.out.println(">> Jumlah yang diambil:");
              obatDiambil = Integer.parseInt(App.input.nextLine());
            } catch (NumberFormatException e) {
              System.out.println("@ Harap hanya memasukkan angka");
            }

            if (obatDiambil > obat.getStok()) {
              System.out.println("@ stok " + obat.getNama() + " hanya tersisa " + obat.getStok());
              break;
            }

            obat.setStok(obat.getStok() - obatDiambil);
            System.out.println("@ Obat berhasil diambil");
            break;
          }
        }

        if (!obatExist) {
          System.out.println("@ Obat tidak ditemukan");
        }
      }

    } while (menu != 0);

  }
}

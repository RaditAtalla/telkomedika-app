package com.dpbo.telkomedika;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class PendaftaranGigi extends Pendaftaran {

  public PendaftaranGigi(Pasien pasien, Dokter dokter, LocalDate tanggal, LocalTime waktu, String keluhan) {
		super(pasien, dokter, tanggal, waktu, keluhan);
	}

  public static void handlePendaftaranGigi() {
    System.out.println("===== Dengan dokter gigi =====");

    System.out.println(">> Keluhan:");
    String keluhan = App.input.nextLine();

    LocalDate tanggal = LocalDate.now();
    try {
      System.out.println(">> Tanggal temu (YY-MM-DD):");
      tanggal = LocalDate.parse(App.input.nextLine());
    } catch (DateTimeParseException e) {
      System.out.println("@ Harap masukkan dengan format sesuai. Contoh: 2025-01-05");
      return;
    }

    LocalTime waktu = LocalTime.now();
    try {
      System.out.println(">> Jam temu (HH:MM):");
      waktu = LocalTime.parse(App.input.nextLine());
    } catch (Exception e) {
      System.out.println("@ Harap masukkan dengan format sesuai. Contoh: 03:59");
      return;
    }

    User dokterGigi = null;
    for (User u : App.users) {
      if (u instanceof DokterGigi) {
        dokterGigi = u;
      }
    }

    PendaftaranGigi pendaftaranGigi = new PendaftaranGigi((Pasien) App.currentUser, (Dokter) dokterGigi, tanggal, waktu, keluhan);
    App.daftarPendaftaran.add(pendaftaranGigi);

    String nomorAntrean = CodeGeneration.generateCode();
    ((Pasien) App.currentUser).setNomorAntrean(nomorAntrean);
    App.daftarAntrean.put(pendaftaranGigi, nomorAntrean);

    System.out.println("@ Pendaftaran Berhasil. Antrean anda adalah " + nomorAntrean);

  }

}
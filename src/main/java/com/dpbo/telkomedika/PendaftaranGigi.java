package com.dpbo.telkomedika;

import java.util.Date;

public class PendaftaranGigi extends Pendaftaran {

    public PendaftaranGigi(String idPendaftaran, Date tanggal, String status) {
        super(idPendaftaran, tanggal, status);
    }

    @Override
    public void daftarOnline() {
        this.setStatus("Terdaftar");
        System.out.println(
                "Pendaftaran gigi dengan ID " + this.getIdPendaftaran() + " berhasil dilakukan secara online.");
        System.out.println("Status pendaftaran: " + this.getStatus());
    }

    @Override
    public void batalkan() {
        this.setStatus("Dibatalkan");
        System.out.println("Pendaftaran gigi dengan ID " + this.getIdPendaftaran() + " dibatalkan.");
        System.out.println("Status pendaftaran: " + this.getStatus());
    }
}
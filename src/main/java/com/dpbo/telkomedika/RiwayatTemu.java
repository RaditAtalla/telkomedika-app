package com.dpbo.telkomedika;


public class RiwayatTemu {
    private Pendaftaran pendaftaran;
    private String laporan;
    private String tindakan;
    private Obat obat;

    public RiwayatTemu(Pendaftaran pendaftaran, String laporan, String tindakan, Obat obat) {
      this.pendaftaran = pendaftaran;
      this.laporan = laporan;
      this.tindakan = tindakan;
      this.obat = obat;
    }

    public Pendaftaran getPendaftaran() {
      return this.pendaftaran;
    }

    public String getLaporan() {
      return this.laporan;
    }

    public String getTindakan() {
      return this.tindakan;
    }

    public Obat getObat() {
      return this.obat;
    }
}

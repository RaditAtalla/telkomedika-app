package com.dpbo.telkomedika;

public class Pasien extends User {
	private String pekerjaan;
	private String alamat;
	private String ttl;
	private String nomorHp;
	private String riwayatKesehatan;
	
	public Pasien(String id, String nama, String fotoProfil, int umur, String pekerjaan, String alamat, String ttl, String nomorHp, String riwayatKesehatan) {
		super(id, nama, fotoProfil, umur);
		// TODO Auto-generated constructor stub
		this.pekerjaan = pekerjaan;
		this.alamat = alamat;
		this.ttl = ttl;
		this.nomorHp = nomorHp;
		this.riwayatKesehatan = riwayatKesehatan;
	}

	public String getPekerjaan() {
		return pekerjaan;
	}

	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public String getNomorHp() {
		return nomorHp;
	}

	public void setNomorHp(String nomorHp) {
		this.nomorHp = nomorHp;
	}

	public String getRiwayatKesehatan() {
		return riwayatKesehatan;
	}

	public void setRiwayatKesehatan(String riwayatKesehatan) {
		this.riwayatKesehatan = riwayatKesehatan;
	}
	
	public void daftar() {}
	
	public void updateData() {}
}

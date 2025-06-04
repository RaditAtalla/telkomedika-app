package com.dpbo.telkomedika;

import java.util.Date;

public abstract class Dokter extends User implements IReferral{

	private String spesialis;
	private Date jadwalPraktek;
	
	public Dokter(String id, String nama, String fotoProfil, int umur, String spesialis, Date jadwalPraktek) {
		super(id, nama, fotoProfil, umur);
		this.spesialis = spesialis;
		this.jadwalPraktek = jadwalPraktek;
	}

	public String getSpesialis() {
		return spesialis;
	}

	public void setSpesialis(String spesialis) {
		this.spesialis = spesialis;
	}

	public Date getJadwalPraktek() {
		return jadwalPraktek;
	}

	public void setJadwalPraktek(Date jadwalPraktek) {
		this.jadwalPraktek = jadwalPraktek;
	}
	
	public abstract void periksaPasien();
	public abstract void diagnosaAkhir();
}

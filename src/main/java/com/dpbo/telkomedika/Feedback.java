package com.dpbo.telkomedika;

public class Feedback {
	public String id;
	public String idPasien;
	public String pesan;
	
	public Feedback(String id, String idPasien, String pesan) {
		super();
		this.id = id;
		this.idPasien = idPasien;
		this.pesan = pesan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(String idPasien) {
		this.idPasien = idPasien;
	}

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}
	
	public void buatPesan() {}
}

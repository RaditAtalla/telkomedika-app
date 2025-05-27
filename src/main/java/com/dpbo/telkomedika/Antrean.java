package com.dpbo.telkomedika;

public class Antrean {
	private String idAntrean;
	private int nomorAntrean;
	private String status;
	
	public Antrean(String idAntrean, int nomorAntrean, String status) {
		super();
		this.idAntrean = idAntrean;
		this.nomorAntrean = nomorAntrean;
		this.status = status;
	}

	public String getIdAntrean() {
		return idAntrean;
	}

	public void setIdAntrean(String idAntrean) {
		this.idAntrean = idAntrean;
	}

	public int getNomorAntrean() {
		return nomorAntrean;
	}

	public void setNomorAntrean(int nomorAntrean) {
		this.nomorAntrean = nomorAntrean;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void ambilNomor() {
		
	}
	
	public void panggilPasien() {
		
	}
}

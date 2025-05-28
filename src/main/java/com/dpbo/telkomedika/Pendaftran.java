package com.dpbo.telkomedika;

import java.util.Date;

public abstract class Pendaftran {

	private String idPendaftaan;
	private Date tanggal;
	private String status
	;
	public Pendaftran(String idPendaftaan, Date tanggal, String status) {
		super();
		this.idPendaftaan = idPendaftaan;
		this.tanggal = tanggal;
		this.status = status;
	}
	
	public String getIdPendaftaan() {
		return idPendaftaan;
	}

	public void setIdPendaftaan(String idPendaftaan) {
		this.idPendaftaan = idPendaftaan;
	}
	
	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public abstract void daftarOnline();
	public abstract void batalkan();
}

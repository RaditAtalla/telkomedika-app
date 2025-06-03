package com.dpbo.telkomedika;

public class Notifikasi {
	private String idNotif;
	private String pesan;
	private boolean status;
	public Notifikasi(String idNotif, String pesan, boolean status) {
		super();
		this.idNotif = idNotif;
		this.pesan = pesan;
		this.status = status;
	}
	public String getIdNotif() {
		return idNotif;
	}
	public void setIdNotif(String idNotif) {
		this.idNotif = idNotif;
	}
	public String getPesan() {
		return pesan;
	}
	public void setPesan(String pesan) {
		this.pesan = pesan;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public void kirimNotif() {
		
	}
	
}

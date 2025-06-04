package com.dpbo.telkomedika;

public abstract class User {
	private String id;
	private String nama;
	private String fotoProfil;
	private int umur;
	
	public User(String id, String nama, String fotoProfil, int umur) {
		super();
		this.id = id;
		this.nama = nama;
		this.fotoProfil = fotoProfil;
		this.umur = umur;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getFotoProfil() {
		return fotoProfil;
	}
	public void setFotoProfil(String fotoProfil) {
		this.fotoProfil = fotoProfil;
	}
	public int getUmur() {
		return umur;
	}
	public void setUmur(int umur) {
		this.umur = umur;
	}	
	public void updateData() {
		
	}
}

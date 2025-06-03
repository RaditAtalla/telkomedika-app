package com.dpbo.telkomedika;

public class Staff extends User{
	private String posisi;

	public Staff(String id, String nama, String fotoProfil, int umur, String posisi) {
		super(id, nama, fotoProfil, umur);
		this.posisi = posisi;
	}

	public String getPosisi() {
		return posisi;
	}

	public void setPosisi(String posisi) {
		this.posisi = posisi;
	}
	
	public void kelolaPengguna() {
		
	}
	
	public void verfikasiData() {
		
	}
	
	public void updateData() {
		
	}
}

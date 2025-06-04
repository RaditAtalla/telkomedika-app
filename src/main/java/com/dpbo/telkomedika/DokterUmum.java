package com.dpbo.telkomedika;

import java.util.Date;

public class DokterUmum extends Dokter{

	public DokterUmum(String id, String nama, String fotoProfil, int umur, String spesialis, Date jadwalPraktek) {
		super(id, nama, fotoProfil, umur, spesialis, jadwalPraktek);
		super.setSpesialis("DokterUmum");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void periksaPasien() {
		// TODO Auto-generated method stub
		System.out.println(super.getId() +super.getNama() + "sedang Diperiksa");
	}

	@Override
	public void diagnosaAkhir() {
		// TODO Auto-generated method stub
		System.out.println(super.getId() + super.getNama() + "telah selesai di diagnosa");
	}

	@Override
	public void refer() {
		// TODO Auto-generated method stub
		
	}
}

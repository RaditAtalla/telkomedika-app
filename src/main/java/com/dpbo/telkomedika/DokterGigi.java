package com.dpbo.telkomedika;

import java.util.Date;

public class DokterGigi extends Dokter{

	public DokterGigi(String id, String nama, String fotoProfil, int umur, String spesialis, Date jadwalPraktek) {
		super(id, nama, fotoProfil, umur, spesialis, jadwalPraktek);
	}

	@Override
	public void periksaPasien() {
		
	}

	@Override
	public void diagnosaAkhir() {
		
	}

	@Override
	public void refer() {
		// TODO Auto-generated method stub
		
	}
}


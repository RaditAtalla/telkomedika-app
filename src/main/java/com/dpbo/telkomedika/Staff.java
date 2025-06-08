package com.dpbo.telkomedika;

public class Staff extends User{
	private String kodePegawai;

  public Staff(String nama, String email, String password, String kodePegawai) {
    super(nama, email, password);
    this.kodePegawai = kodePegawai;
  }

  public String getKodePegawai() {
    return this.kodePegawai;
  }
}

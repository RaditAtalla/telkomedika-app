package com.dpbo.telkomedika;

public abstract class User {
  private String nama;
  private String email;
  private String password;

  public User(String nama, String email, String password) {
    this.nama = nama;
    this.email = email;
    this.password = password;
  }

  public String getNama() {
    return this.nama;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  @Override
  public String toString() {
    return "Nama: " + nama + "\nEmail: " + email + "\n";
  }
}

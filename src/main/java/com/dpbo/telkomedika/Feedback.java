package com.dpbo.telkomedika;

public class Feedback {
  private User pengirim;
	private String konten;

  public Feedback(User pengirim, String konten) {
    this.pengirim = pengirim;
    this.konten = konten;
  }

  public User getPengirim() {
    return this.pengirim;
  }

  public String getKonten() {
    return this.konten;
  }

}

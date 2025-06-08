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

  @Override
  public String toString() {
    return "Pengirim: " + this.pengirim.getEmail() + "\nKonten: " + this.konten;
  }

  public static void viewAllFeedbacks() {
    System.out.println("===== Daftar Feedback =====");
    int i = 1;
    if (App.feedbacks.size() > 0) {
      for (Feedback feedback : App.feedbacks) {
        System.out.println(i + ". " + feedback);
        i++;
      }
    } else {
      System.out.println("@ Tidak ada feedback");
    }
  }

}

package com.dpbo.telkomedika.gui.pasien;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PanggilAmbulansView extends VBox {
    public PanggilAmbulansView() {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Panggil Ambulans");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        Label lokasiLbl = new Label("Lokasi Penjemputan:");
        TextField lokasiField = new TextField();
        lokasiField.setPromptText("Masukkan lokasi penjemputan");

        Button panggilBtn = new Button("Panggil Ambulans");
        panggilBtn.setOnAction(e -> {
            String lokasi = lokasiField.getText().trim();
            if (lokasi.isEmpty()) {
                showAlert("Input salah", "Lokasi penjemputan harus diisi!");
                return;
            }
            showAlert("Berhasil", "Ambulans segera menuju " + lokasi);
            lokasiField.clear();
        });

        getChildren().addAll(title, separator, lokasiLbl, lokasiField, panggilBtn);
        setMaxWidth(Double.MAX_VALUE);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

package com.dpbo.telkomedika.gui.staff;

import com.dpbo.telkomedika.App;
import com.dpbo.telkomedika.Notifikasi;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class KirimNotifikasiView extends VBox {
    public KirimNotifikasiView() {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Kirim Notifikasi ke Pasien");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        Label judulLbl = new Label("Judul:");
        TextField judulField = new TextField();
        judulField.setPromptText("Masukkan judul notifikasi");

        Label isiLbl = new Label("Isi:");
        TextArea isiArea = new TextArea();
        isiArea.setPromptText("Masukkan isi notifikasi");
        isiArea.setPrefRowCount(3);

        Button kirimBtn = new Button("Kirim");
        kirimBtn.setOnAction(e -> {
            String judul = judulField.getText().trim();
            String isi = isiArea.getText().trim();
            if (judul.isEmpty() || isi.isEmpty()) {
                showAlert("Input salah", "Judul dan isi notifikasi harus diisi!");
                return;
            }
            Notifikasi notifikasi = new Notifikasi(judul, isi);
            App.notifications.add(notifikasi);
            showAlert("Berhasil", "Notifikasi berhasil dikirim.");
            judulField.clear();
            isiArea.clear();
        });

        getChildren().addAll(title, separator, judulLbl, judulField, isiLbl, isiArea, kirimBtn);
        setMaxWidth(Double.MAX_VALUE);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

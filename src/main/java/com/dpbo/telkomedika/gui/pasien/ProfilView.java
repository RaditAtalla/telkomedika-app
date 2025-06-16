package com.dpbo.telkomedika.gui.pasien;

import com.dpbo.telkomedika.Pasien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProfilView extends VBox {
    public ProfilView(Pasien pasien) {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Profil Pasien");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        Label namaLbl = new Label("Nama: " + pasien.getNama());
        Label emailLbl = new Label("Email: " + pasien.getEmail());
        Label nomorIndukLbl = new Label("Nomor Induk: " + pasien.getNomorInduk());

        // Daftar riwayat penyakit dalam ListView
        Label riwayatLbl = new Label("Riwayat Penyakit:");

        ListView<String> riwayatList = new ListView<>();
        riwayatList.setPlaceholder(new Label("Belum ada riwayat penyakit yang ditambahkan."));
        ObservableList<String> data = FXCollections.observableArrayList(pasien.getRiwayatPenyakit());
        riwayatList.setItems(data);

        // Tombol tambah riwayat penyakit
        Button tambahBtn = new Button("Tambah");
        tambahBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Tambah Riwayat Penyakit");
            dialog.setHeaderText("Masukkan nama penyakit baru");
            dialog.setContentText("Penyakit:");
            dialog.showAndWait().ifPresent(penyakitBaru -> {
                penyakitBaru = penyakitBaru.trim();
                if (!penyakitBaru.isEmpty()) {
                    pasien.getRiwayatPenyakit().add(penyakitBaru);
                    data.add(penyakitBaru);
                    showAlert("Berhasil", "Riwayat penyakit berhasil ditambahkan.");
                } else {
                    showAlert("Input salah", "Field tidak boleh kosong.");
                }
            });
        });

        // Tombol hapus riwayat penyakit
        Button hapusBtn = new Button("Hapus");
        hapusBtn.setOnAction(e -> {
            String selected = riwayatList.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Pilih Data", "Pilih riwayat penyakit yang ingin dihapus.");
                return;
            }
            pasien.getRiwayatPenyakit().remove(selected);
            data.remove(selected);
            showAlert("Berhasil", "Riwayat penyakit berhasil dihapus.");
        });

        HBox tombolBox = new HBox(10, tambahBtn, hapusBtn);

        getChildren().addAll(
            title, separator,
            namaLbl, emailLbl, nomorIndukLbl,
            riwayatLbl, riwayatList, tombolBox
        );
        setMaxWidth(Double.MAX_VALUE);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

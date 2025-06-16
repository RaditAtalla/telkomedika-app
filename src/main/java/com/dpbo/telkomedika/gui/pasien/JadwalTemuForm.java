package com.dpbo.telkomedika.gui.pasien;

import com.dpbo.telkomedika.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class JadwalTemuForm extends VBox {
    public JadwalTemuForm(Pasien pasien) {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Buat Jadwal Temu");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        // Jenis Dokter
        Label dokterLbl = new Label("Jenis Dokter");
        ComboBox<String> dokterBox = new ComboBox<>();
        dokterBox.getItems().addAll("Dokter Umum", "Dokter Gigi");
        dokterBox.setPromptText("Pilih dokter");
        dokterBox.setMaxWidth(Double.MAX_VALUE);

        // Tanggal
        Label tanggalLbl = new Label("Tanggal Temu");
        DatePicker tanggalPicker = new DatePicker();
        tanggalPicker.setPromptText("Pilih tanggal");
        tanggalPicker.setMaxWidth(Double.MAX_VALUE);

        // Jam
        Label jamLbl = new Label("Jam Temu");
        ComboBox<String> jamBox = new ComboBox<>();
        jamBox.setPromptText("Pilih jam");
        IntStream.rangeClosed(8, 16).forEach(hour -> {
            jamBox.getItems().add(String.format("%02d:00", hour));
            if (hour < 16) jamBox.getItems().add(String.format("%02d:30", hour));
        });
        jamBox.setMaxWidth(Double.MAX_VALUE);

        // Keluhan
        Label keluhanLbl = new Label("Keluhan");
        TextArea keluhanArea = new TextArea();
        keluhanArea.setPromptText("Tulis keluhan Anda");
        keluhanArea.setPrefRowCount(2);
        keluhanArea.setMaxWidth(Double.MAX_VALUE);

        // Tombol & hasil
        Button submitBtn = new Button("Daftar");
        submitBtn.setMaxWidth(Double.MAX_VALUE);

        submitBtn.setOnAction(e -> {
            // Cek apakah pasien sudah punya nomor antrean aktif
            if (pasien.getNomorAntrean() != null && !pasien.getNomorAntrean().isEmpty()) {
                showAlert("Pendaftaran Gagal", "Anda sudah melakukan pendaftaran. Selesaikan dulu jadwal temu sebelumnya sebelum membuat pendaftaran baru.");
                return;
            }

            String jenisDokter = dokterBox.getValue();
            LocalDate tanggal = tanggalPicker.getValue();
            String jamStr = jamBox.getValue();
            String keluhan = keluhanArea.getText();

            if (jenisDokter == null || tanggal == null || jamStr == null || keluhan.isEmpty()) {
                showAlert("Input salah", "Silakan lengkapi semua field!");
                return;
            }

            // Generate nomor antrean
            String nomorAntrean = CodeGeneration.generateCode();
            pasien.setNomorAntrean(nomorAntrean);

            java.time.LocalTime waktu = java.time.LocalTime.parse(jamStr);
            Dokter dokter = null;
            for (User u : App.users) {
                if (jenisDokter.equals("Dokter Umum") && u instanceof DokterUmum) {
                    dokter = (Dokter) u;
                    break;
                } else if (jenisDokter.equals("Dokter Gigi") && u instanceof DokterGigi) {
                    dokter = (Dokter) u;
                    break;
                }
            }
            if (dokter == null) {
                showAlert("Pendaftaran Gagal", "Dokter tidak ditemukan.");
                return;
            }

            Pendaftaran pendaftaran;
            if (jenisDokter.equals("Dokter Umum")) {
                pendaftaran = new PendaftaranUmum(pasien, dokter, tanggal, waktu, keluhan);
            } else {
                pendaftaran = new PendaftaranGigi(pasien, dokter, tanggal, waktu, keluhan);
            }
            App.daftarPendaftaran.add(pendaftaran);
            App.daftarAntrean.put(pendaftaran, nomorAntrean);

            showAlert("Berhasil", "Antrean anda adalah " + nomorAntrean);

            // Reset field setelah berhasil daftar
            dokterBox.setValue(null);
            tanggalPicker.setValue(null);
            jamBox.setValue(null);
            keluhanArea.clear();
        });

        getChildren().addAll(
            title, separator,
            dokterLbl, dokterBox,
            tanggalLbl, tanggalPicker,
            jamLbl, jamBox,
            keluhanLbl, keluhanArea,
            submitBtn
        );
        setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(keluhanArea, Priority.ALWAYS);
    }

    // Utility alert popup, sama seperti popup tambah riwayat penyakit ketika salah input
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
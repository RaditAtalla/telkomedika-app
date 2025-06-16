package com.dpbo.telkomedika.gui.dokter;

import com.dpbo.telkomedika.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class JadwalTemuDokterView extends VBox {
    public JadwalTemuDokterView(Dokter dokter) {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Jadwal Temu Anda");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        TableView<JadwalRow> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Belum ada jadwal temu untuk Anda."));

        TableColumn<JadwalRow, Number> noCol = new TableColumn<>("No");
        noCol.setCellValueFactory(cellData -> cellData.getValue().noProperty());

        TableColumn<JadwalRow, String> pasienCol = new TableColumn<>("Pasien");
        pasienCol.setCellValueFactory(cellData -> cellData.getValue().pasienProperty());

        TableColumn<JadwalRow, String> tanggalCol = new TableColumn<>("Tanggal");
        tanggalCol.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());

        TableColumn<JadwalRow, String> jamCol = new TableColumn<>("Jam");
        jamCol.setCellValueFactory(cellData -> cellData.getValue().jamProperty());

        TableColumn<JadwalRow, String> keluhanCol = new TableColumn<>("Keluhan");
        keluhanCol.setCellValueFactory(cellData -> cellData.getValue().keluhanProperty());

        TableColumn<JadwalRow, Void> aksiCol = new TableColumn<>("Aksi");
        aksiCol.setCellFactory(col -> new TableCell<JadwalRow, Void>() {
            private final Button buatLaporanBtn = new Button("Buat Laporan");

            {
                buatLaporanBtn.setStyle("-fx-cursor: hand;");
                buatLaporanBtn.setOnAction(e -> {
                    JadwalRow row = getTableView().getItems().get(getIndex());
                    showLaporanPopup(row.pendaftaran);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : buatLaporanBtn);
            }
        });

        table.getColumns().addAll(noCol, pasienCol, tanggalCol, jamCol, keluhanCol, aksiCol);

        ObservableList<JadwalRow> data = FXCollections.observableArrayList();
        int i = 1;
        for (Pendaftaran p : App.daftarPendaftaran) {
            if (
                p.getDokter().getEmail().equals(dokter.getEmail())
                && !isLaporanSudahAda(p)
            ) {
                data.add(new JadwalRow(
                    i++,
                    p.getPasien().getNama(),
                    p.getTanggal().toString(),
                    p.getWaktu().toString(),
                    p.keluhan(),
                    p
                ));
            }
        }
        table.setItems(data);

        getChildren().addAll(title, separator, table);
        setMaxWidth(Double.MAX_VALUE);
    }

    // Cek apakah laporan sudah ada di riwayat temu pasien
    private boolean isLaporanSudahAda(Pendaftaran pendaftaran) {
        for (RiwayatTemu r : pendaftaran.getPasien().getRiwayatTemu()) {
            Pendaftaran p = r.getPendaftaran();
            if (
                p.getTanggal().equals(pendaftaran.getTanggal()) &&
                p.getWaktu().equals(pendaftaran.getWaktu()) &&
                p.getDokter().getEmail().equals(pendaftaran.getDokter().getEmail()) &&
                p.getPasien().getEmail().equals(pendaftaran.getPasien().getEmail())
            ) {
                return true;
            }
        }
        return false;
    }

    private void showLaporanPopup(Pendaftaran pendaftaran) {
        Dialog<LaporanInput> dialog = new Dialog<>();
        dialog.setTitle("Buat Laporan Temu");
        dialog.setHeaderText("Buat laporan untuk pasien: " + pendaftaran.getPasien().getNama());

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label laporanLbl = new Label("Laporan:");
        TextArea laporanArea = new TextArea();
        laporanArea.setPrefRowCount(2);

        Label tindakanLbl = new Label("Tindakan:");
        TextField tindakanField = new TextField();

        Label obatLbl = new Label("Obat:");
        // Filter hanya obat dengan stok > 0
        ObservableList<Obat> obatList = FXCollections.observableArrayList();
        for (Obat o : App.daftarObat) {
            if (o.getStok() > 0) {
                obatList.add(o);
            }
        }
        ComboBox<Obat> obatBox = new ComboBox<>(obatList);
        obatBox.setPromptText("Pilih obat");
        // Tampilkan hanya nama obat di dropdown
        obatBox.setCellFactory(cb -> new ListCell<Obat>() {
            @Override
            protected void updateItem(Obat item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNama());
            }
        });
        obatBox.setButtonCell(new ListCell<Obat>() {
            @Override
            protected void updateItem(Obat item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNama());
            }
        });

        grid.add(laporanLbl, 0, 0);
        grid.add(laporanArea, 1, 0);
        grid.add(tindakanLbl, 0, 1);
        grid.add(tindakanField, 1, 1);
        grid.add(obatLbl, 0, 2);
        grid.add(obatBox, 1, 2);

        dialog.getDialogPane().setContent(grid);

        ButtonType okButtonType = new ButtonType("Simpan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return new LaporanInput(
                    laporanArea.getText().trim(),
                    tindakanField.getText().trim(),
                    obatBox.getValue()
                );
            }
            return null;
        });

        dialog.showAndWait().ifPresent(input -> {
            if (input.laporan.isEmpty() || input.tindakan.isEmpty() || input.obat == null) {
                showAlert("Input salah", "Semua field harus diisi!");
                return;
            }
            RiwayatTemu riwayat = new RiwayatTemu(pendaftaran, input.laporan, input.tindakan, input.obat);
            Pasien pasien = pendaftaran.getPasien();
            pasien.getRiwayatTemu().add(riwayat);
            pasien.setNomorAntrean("");
            App.daftarPendaftaran.remove(pendaftaran);

            showAlert("Berhasil", "Laporan berhasil disimpan.");
            this.getChildren().clear();
            this.getChildren().addAll(new JadwalTemuDokterView(pendaftaran.getDokter()));
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class JadwalRow {
        private final SimpleIntegerProperty no;
        private final SimpleStringProperty pasien;
        private final SimpleStringProperty tanggal;
        private final SimpleStringProperty jam;
        private final SimpleStringProperty keluhan;
        private final Pendaftaran pendaftaran;

        public JadwalRow(int no, String pasien, String tanggal, String jam, String keluhan, Pendaftaran pendaftaran) {
            this.no = new SimpleIntegerProperty(no);
            this.pasien = new SimpleStringProperty(pasien);
            this.tanggal = new SimpleStringProperty(tanggal);
            this.jam = new SimpleStringProperty(jam);
            this.keluhan = new SimpleStringProperty(keluhan);
            this.pendaftaran = pendaftaran;
        }

        public SimpleIntegerProperty noProperty() { return no; }
        public SimpleStringProperty pasienProperty() { return pasien; }
        public SimpleStringProperty tanggalProperty() { return tanggal; }
        public SimpleStringProperty jamProperty() { return jam; }
        public SimpleStringProperty keluhanProperty() { return keluhan; }
    }

    private static class LaporanInput {
        String laporan;
        String tindakan;
        Obat obat;
        LaporanInput(String laporan, String tindakan, Obat obat) {
            this.laporan = laporan;
            this.tindakan = tindakan;
            this.obat = obat;
        }
    }
}

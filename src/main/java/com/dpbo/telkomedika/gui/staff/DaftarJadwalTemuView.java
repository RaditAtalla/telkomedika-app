package com.dpbo.telkomedika.gui.staff;

import com.dpbo.telkomedika.App;
import com.dpbo.telkomedika.Pendaftaran;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class DaftarJadwalTemuView extends VBox {
    public DaftarJadwalTemuView() {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Daftar Jadwal Temu");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        TableView<PendaftaranRow> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Belum ada jadwal temu yang terdaftar."));

        TableColumn<PendaftaranRow, Number> noCol = new TableColumn<>("No");
        noCol.setCellValueFactory(cellData -> cellData.getValue().noProperty());

        TableColumn<PendaftaranRow, String> pasienCol = new TableColumn<>("Pasien");
        pasienCol.setCellValueFactory(cellData -> cellData.getValue().pasienProperty());

        TableColumn<PendaftaranRow, String> dokterCol = new TableColumn<>("Dokter");
        dokterCol.setCellValueFactory(cellData -> cellData.getValue().dokterProperty());

        TableColumn<PendaftaranRow, String> tanggalCol = new TableColumn<>("Tanggal");
        tanggalCol.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());

        TableColumn<PendaftaranRow, String> jamCol = new TableColumn<>("Jam");
        jamCol.setCellValueFactory(cellData -> cellData.getValue().jamProperty());

        TableColumn<PendaftaranRow, String> keluhanCol = new TableColumn<>("Keluhan");
        keluhanCol.setCellValueFactory(cellData -> cellData.getValue().keluhanProperty());

        table.getColumns().addAll(noCol, pasienCol, dokterCol, tanggalCol, jamCol, keluhanCol);

        ObservableList<PendaftaranRow> data = FXCollections.observableArrayList();
        int i = 1;
        for (Pendaftaran p : App.daftarPendaftaran) {
            data.add(new PendaftaranRow(
                i++,
                p.getPasien().getNama(),
                p.getDokter().getNama(),
                p.getTanggal().toString(),
                p.getWaktu().toString(),
                p.keluhan()
            ));
        }
        table.setItems(data);

        getChildren().addAll(title, separator, table);
        setMaxWidth(Double.MAX_VALUE);
    }

    public static class PendaftaranRow {
        private final javafx.beans.property.SimpleIntegerProperty no;
        private final javafx.beans.property.SimpleStringProperty pasien;
        private final javafx.beans.property.SimpleStringProperty dokter;
        private final javafx.beans.property.SimpleStringProperty tanggal;
        private final javafx.beans.property.SimpleStringProperty jam;
        private final javafx.beans.property.SimpleStringProperty keluhan;

        public PendaftaranRow(int no, String pasien, String dokter, String tanggal, String jam, String keluhan) {
            this.no = new javafx.beans.property.SimpleIntegerProperty(no);
            this.pasien = new javafx.beans.property.SimpleStringProperty(pasien);
            this.dokter = new javafx.beans.property.SimpleStringProperty(dokter);
            this.tanggal = new javafx.beans.property.SimpleStringProperty(tanggal);
            this.jam = new javafx.beans.property.SimpleStringProperty(jam);
            this.keluhan = new javafx.beans.property.SimpleStringProperty(keluhan);
        }

        public javafx.beans.property.SimpleIntegerProperty noProperty() { return no; }
        public javafx.beans.property.SimpleStringProperty pasienProperty() { return pasien; }
        public javafx.beans.property.SimpleStringProperty dokterProperty() { return dokter; }
        public javafx.beans.property.SimpleStringProperty tanggalProperty() { return tanggal; }
        public javafx.beans.property.SimpleStringProperty jamProperty() { return jam; }
        public javafx.beans.property.SimpleStringProperty keluhanProperty() { return keluhan; }
    }
}

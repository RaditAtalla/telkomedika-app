package com.dpbo.telkomedika.gui.pasien;

import com.dpbo.telkomedika.App;
import com.dpbo.telkomedika.Pasien;
import com.dpbo.telkomedika.Pendaftaran;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NomorAntreanView extends VBox {
    public NomorAntreanView(Pasien pasien) {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Lihat Nomor Antrean");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        String nomor = pasien.getNomorAntrean();
        Label nomorLabel = new Label((nomor == null || nomor.isEmpty()) ? "(!) Anda Belum terdaftar antrean." : nomor);

        Label labelAntreanAnda = new Label("Nomor antrean Anda:");

        // TableView untuk seluruh antrean
        Label daftarTitle = new Label("Urutan Seluruh Nomor Antrean:");
        daftarTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TableView<PendaftaranAntreanRow> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Belum ada antrean terdaftar."));

        TableColumn<PendaftaranAntreanRow, Number> noCol = new TableColumn<>("No");
        noCol.setCellValueFactory(cellData -> cellData.getValue().noProperty());

        TableColumn<PendaftaranAntreanRow, String> pasienCol = new TableColumn<>("Pasien");
        pasienCol.setCellValueFactory(cellData -> cellData.getValue().pasienProperty());

        TableColumn<PendaftaranAntreanRow, String> dokterCol = new TableColumn<>("Dokter");
        dokterCol.setCellValueFactory(cellData -> cellData.getValue().dokterProperty());

        TableColumn<PendaftaranAntreanRow, String> tanggalCol = new TableColumn<>("Tanggal");
        tanggalCol.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());

        TableColumn<PendaftaranAntreanRow, String> jamCol = new TableColumn<>("Jam");
        jamCol.setCellValueFactory(cellData -> cellData.getValue().jamProperty());

        TableColumn<PendaftaranAntreanRow, String> kodeCol = new TableColumn<>("Nomor Antrean");
        kodeCol.setCellValueFactory(cellData -> cellData.getValue().kodeProperty());

        table.getColumns().addAll(noCol, pasienCol, dokterCol, tanggalCol, jamCol, kodeCol);

        ObservableList<PendaftaranAntreanRow> data = FXCollections.observableArrayList();

        // Ambil dari daftarPendaftaran agar urutan sesuai waktu pendaftaran
        List<Pendaftaran> antreanList = new ArrayList<>(App.daftarPendaftaran);
        antreanList.sort(Comparator
            .comparing(Pendaftaran::getTanggal)
            .thenComparing(Pendaftaran::getWaktu));

        int i = 1;
        for (Pendaftaran pendaftaran : antreanList) {
            String kodeAntrean = App.daftarAntrean.get(pendaftaran);
            if (kodeAntrean == null) continue; // skip jika tidak ada kode antrean
            data.add(new PendaftaranAntreanRow(
                i++,
                pendaftaran.getPasien().getNama(),
                pendaftaran.getDokter().getNama(),
                pendaftaran.getTanggal().toString(),
                pendaftaran.getWaktu().toString(),
                kodeAntrean
            ));
        }

        table.setItems(data);

        getChildren().addAll(
            title, separator,
            labelAntreanAnda, nomorLabel,
            daftarTitle, table
        );
        setMaxWidth(Double.MAX_VALUE);
    }

    // Inner class untuk row table
    public static class PendaftaranAntreanRow {
        private final javafx.beans.property.SimpleIntegerProperty no;
        private final javafx.beans.property.SimpleStringProperty pasien;
        private final javafx.beans.property.SimpleStringProperty dokter;
        private final javafx.beans.property.SimpleStringProperty tanggal;
        private final javafx.beans.property.SimpleStringProperty jam;
        private final javafx.beans.property.SimpleStringProperty kode;

        public PendaftaranAntreanRow(int no, String pasien, String dokter, String tanggal, String jam, String kode) {
            this.no = new javafx.beans.property.SimpleIntegerProperty(no);
            this.pasien = new javafx.beans.property.SimpleStringProperty(pasien);
            this.dokter = new javafx.beans.property.SimpleStringProperty(dokter);
            this.tanggal = new javafx.beans.property.SimpleStringProperty(tanggal);
            this.jam = new javafx.beans.property.SimpleStringProperty(jam);
            this.kode = new javafx.beans.property.SimpleStringProperty(kode);
        }

        public javafx.beans.property.SimpleIntegerProperty noProperty() { return no; }
        public javafx.beans.property.SimpleStringProperty pasienProperty() { return pasien; }
        public javafx.beans.property.SimpleStringProperty dokterProperty() { return dokter; }
        public javafx.beans.property.SimpleStringProperty tanggalProperty() { return tanggal; }
        public javafx.beans.property.SimpleStringProperty jamProperty() { return jam; }
        public javafx.beans.property.SimpleStringProperty kodeProperty() { return kode; }
    }
}

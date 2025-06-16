package com.dpbo.telkomedika.gui.pasien;

import com.dpbo.telkomedika.Pasien;
import com.dpbo.telkomedika.RiwayatTemu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RiwayatTemuView extends VBox {
    public RiwayatTemuView(Pasien pasien) {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Riwayat Temu");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        TableView<RiwayatTemuRow> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Belum ada riwayat temu."));

        TableColumn<RiwayatTemuRow, Number> noCol = new TableColumn<>("No");
        noCol.setCellValueFactory(cellData -> cellData.getValue().noProperty());

        TableColumn<RiwayatTemuRow, String> dokterCol = new TableColumn<>("Dokter");
        dokterCol.setCellValueFactory(cellData -> cellData.getValue().dokterProperty());

        TableColumn<RiwayatTemuRow, String> tanggalCol = new TableColumn<>("Tanggal");
        tanggalCol.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());

        TableColumn<RiwayatTemuRow, String> jamCol = new TableColumn<>("Jam");
        jamCol.setCellValueFactory(cellData -> cellData.getValue().jamProperty());

        TableColumn<RiwayatTemuRow, String> laporanCol = new TableColumn<>("Laporan");
        laporanCol.setCellValueFactory(cellData -> cellData.getValue().laporanProperty());

        TableColumn<RiwayatTemuRow, String> tindakanCol = new TableColumn<>("Tindakan");
        tindakanCol.setCellValueFactory(cellData -> cellData.getValue().tindakanProperty());

        TableColumn<RiwayatTemuRow, String> obatCol = new TableColumn<>("Obat");
        obatCol.setCellValueFactory(cellData -> cellData.getValue().obatProperty());

        table.getColumns().addAll(noCol, dokterCol, tanggalCol, jamCol, laporanCol, tindakanCol, obatCol);

        ObservableList<RiwayatTemuRow> data = FXCollections.observableArrayList();
        int i = 1;
        for (RiwayatTemu riwayat : pasien.getRiwayatTemu()) {
            data.add(new RiwayatTemuRow(
                i++,
                riwayat.getPendaftaran().getDokter().getNama(),
                riwayat.getPendaftaran().getTanggal().toString(),
                riwayat.getPendaftaran().getWaktu().toString(),
                riwayat.getLaporan(),
                riwayat.getTindakan(),
                riwayat.getObat().getNama()
            ));
        }
        table.setItems(data);

        getChildren().addAll(title, separator, table);
        setMaxWidth(Double.MAX_VALUE);
    }

    public static class RiwayatTemuRow {
        private final javafx.beans.property.SimpleIntegerProperty no;
        private final javafx.beans.property.SimpleStringProperty dokter;
        private final javafx.beans.property.SimpleStringProperty tanggal;
        private final javafx.beans.property.SimpleStringProperty jam;
        private final javafx.beans.property.SimpleStringProperty laporan;
        private final javafx.beans.property.SimpleStringProperty tindakan;
        private final javafx.beans.property.SimpleStringProperty obat;

        public RiwayatTemuRow(int no, String dokter, String tanggal, String jam, String laporan, String tindakan, String obat) {
            this.no = new javafx.beans.property.SimpleIntegerProperty(no);
            this.dokter = new javafx.beans.property.SimpleStringProperty(dokter);
            this.tanggal = new javafx.beans.property.SimpleStringProperty(tanggal);
            this.jam = new javafx.beans.property.SimpleStringProperty(jam);
            this.laporan = new javafx.beans.property.SimpleStringProperty(laporan);
            this.tindakan = new javafx.beans.property.SimpleStringProperty(tindakan);
            this.obat = new javafx.beans.property.SimpleStringProperty(obat);
        }

        public javafx.beans.property.SimpleIntegerProperty noProperty() { return no; }
        public javafx.beans.property.SimpleStringProperty dokterProperty() { return dokter; }
        public javafx.beans.property.SimpleStringProperty tanggalProperty() { return tanggal; }
        public javafx.beans.property.SimpleStringProperty jamProperty() { return jam; }
        public javafx.beans.property.SimpleStringProperty laporanProperty() { return laporan; }
        public javafx.beans.property.SimpleStringProperty tindakanProperty() { return tindakan; }
        public javafx.beans.property.SimpleStringProperty obatProperty() { return obat; }
    }
}

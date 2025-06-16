package com.dpbo.telkomedika.gui.pasien;

import com.dpbo.telkomedika.Notifikasi;
import com.dpbo.telkomedika.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class NotifikasiView extends VBox {
    public NotifikasiView() {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Daftar Notifikasi");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        TableView<NotifikasiRow> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Belum ada notifikasi yang masuk."));

        TableColumn<NotifikasiRow, String> judulCol = new TableColumn<>("Judul");
        judulCol.setCellValueFactory(cellData -> cellData.getValue().judulProperty());

        TableColumn<NotifikasiRow, String> isiCol = new TableColumn<>("Isi");
        isiCol.setCellValueFactory(cellData -> cellData.getValue().isiProperty());

        table.getColumns().addAll(judulCol, isiCol);

        ObservableList<NotifikasiRow> data = FXCollections.observableArrayList();
        for (Notifikasi notif : App.notifications) {
            data.add(new NotifikasiRow(notif.getJudul(), notif.getIsi()));
        }
        table.setItems(data);

        getChildren().addAll(title, separator, table);
        setMaxWidth(Double.MAX_VALUE);
    }

    public static class NotifikasiRow {
        private final javafx.beans.property.SimpleStringProperty judul;
        private final javafx.beans.property.SimpleStringProperty isi;

        public NotifikasiRow(String judul, String isi) {
            this.judul = new javafx.beans.property.SimpleStringProperty(judul);
            this.isi = new javafx.beans.property.SimpleStringProperty(isi);
        }

        public javafx.beans.property.SimpleStringProperty judulProperty() { return judul; }
        public javafx.beans.property.SimpleStringProperty isiProperty() { return isi; }
    }
}

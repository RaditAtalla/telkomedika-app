package com.dpbo.telkomedika.gui.staff;

import com.dpbo.telkomedika.App;
import com.dpbo.telkomedika.Feedback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class DaftarFeedbackView extends VBox {
    public DaftarFeedbackView() {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Daftar Feedback");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        TableView<FeedbackRow> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Belum ada feedback yang masuk."));

        TableColumn<FeedbackRow, String> pengirimCol = new TableColumn<>("Pengirim");
        pengirimCol.setCellValueFactory(cellData -> cellData.getValue().pengirimProperty());

        TableColumn<FeedbackRow, String> isiCol = new TableColumn<>("Isi Feedback");
        isiCol.setCellValueFactory(cellData -> cellData.getValue().isiProperty());

        table.getColumns().addAll(pengirimCol, isiCol);

        ObservableList<FeedbackRow> data = FXCollections.observableArrayList();
        for (Feedback f : App.feedbacks) {
            data.add(new FeedbackRow(f.getPengirim().getNama(), f.getKonten()));
        }
        table.setItems(data);

        getChildren().addAll(title, separator, table);
        setMaxWidth(Double.MAX_VALUE);
    }

    public static class FeedbackRow {
        private final javafx.beans.property.SimpleStringProperty pengirim;
        private final javafx.beans.property.SimpleStringProperty isi;

        public FeedbackRow(String pengirim, String isi) {
            this.pengirim = new javafx.beans.property.SimpleStringProperty(pengirim);
            this.isi = new javafx.beans.property.SimpleStringProperty(isi);
        }

        public javafx.beans.property.SimpleStringProperty pengirimProperty() { return pengirim; }
        public javafx.beans.property.SimpleStringProperty isiProperty() { return isi; }
    }
}

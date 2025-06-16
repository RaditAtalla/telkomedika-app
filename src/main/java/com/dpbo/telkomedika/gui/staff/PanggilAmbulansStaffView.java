package com.dpbo.telkomedika.gui.staff;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PanggilAmbulansStaffView extends VBox {
    public PanggilAmbulansStaffView() {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Panggil Ambulans Rumah Sakit");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        Button panggilBtn = new Button("Panggil Ambulans");
        panggilBtn.setOnAction(e -> {
            showAlert("Berhasil", "Ambulans segera menuju Telkomedika");
        });

        getChildren().addAll(title, separator, panggilBtn);
        setMaxWidth(Double.MAX_VALUE);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

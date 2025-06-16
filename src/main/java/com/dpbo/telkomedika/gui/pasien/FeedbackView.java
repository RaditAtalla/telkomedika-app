package com.dpbo.telkomedika.gui.pasien;

import com.dpbo.telkomedika.App;
import com.dpbo.telkomedika.Feedback;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class FeedbackView extends VBox {
    public FeedbackView() {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Kirim Feedback");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        Label kontenLbl = new Label("Isi Feedback:");
        TextArea kontenArea = new TextArea();
        kontenArea.setPromptText("Tulis feedback Anda di sini");
        kontenArea.setPrefRowCount(3);

        Button kirimBtn = new Button("Kirim");
        kirimBtn.setOnAction(e -> {
            String konten = kontenArea.getText().trim();
            if (konten.isEmpty()) {
                showAlert("Input salah", "Feedback tidak boleh kosong!");
                return;
            }
            Feedback feedback = new Feedback(App.currentUser, konten);
            App.feedbacks.add(feedback);
            showAlert("Berhasil", "Feedback berhasil dikirim.");
            kontenArea.clear();
        });

        getChildren().addAll(title, separator, kontenLbl, kontenArea, kirimBtn);
        setMaxWidth(Double.MAX_VALUE);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

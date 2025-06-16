package com.dpbo.telkomedika.gui;

import com.dpbo.telkomedika.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LoginView extends VBox {
    public LoginView() {
        setSpacing(20);
        setPadding(new Insets(40));
        setAlignment(Pos.CENTER);

        Label title = new Label("Selamat Datang di Telkomedika");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(Double.MAX_VALUE);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(Double.MAX_VALUE);

        Button loginBtn = new Button("Login");
        loginBtn.setMaxWidth(Double.MAX_VALUE);

        Label msg = new Label();
        msg.setStyle("-fx-text-fill: red;");

        loginBtn.setOnAction(e -> {
            AppBiz biz = new AppBiz();
            User user = biz.login(emailField.getText(), passwordField.getText());
            if (user == null) {
                msg.setText("Login gagal. Cek email/password.");
            } else {
                App.currentUser = user; // <-- Tambahkan baris ini!
                MainApp.showHome(user);
            }
        });

        getChildren().addAll(title, emailField, passwordField, loginBtn, msg);
        setPrefWidth(350);
    }
}
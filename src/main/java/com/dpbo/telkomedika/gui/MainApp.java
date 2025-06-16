package com.dpbo.telkomedika.gui;

import com.dpbo.telkomedika.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        primaryStage.setTitle("Telkomedika App");
        // Ubah ukuran login window jadi lebih kecil, misal 400x500
        primaryStage.setScene(new Scene(new LoginView(), 500, 300));
        primaryStage.show();
    }

    public static void showLogin() {
        Scene scene = new Scene(new LoginView(), 500, 300); // Ukuran kecil juga di sini
        mainStage.setScene(scene);
        mainStage.setTitle("Telkomedika - Login");
        mainStage.show();
        // Jangan setMaximized di sini, biarkan login window normal
    }

    public static void showHome(User user) {
        Scene scene = new Scene(new HomeView(user));
        mainStage.setScene(scene);
        mainStage.setTitle("Telkomedika - Home");
        mainStage.show();
        mainStage.setMaximized(true); // HANYA di sini agar Home selalu full window
    }

    public static void main(String[] args) {
        launch(args);
    }
}
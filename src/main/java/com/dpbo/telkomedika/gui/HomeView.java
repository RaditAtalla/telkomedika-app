package com.dpbo.telkomedika.gui;

import com.dpbo.telkomedika.*;
import com.dpbo.telkomedika.gui.dokter.JadwalTemuDokterView;
import com.dpbo.telkomedika.gui.pasien.FeedbackView;
import com.dpbo.telkomedika.gui.pasien.JadwalTemuForm;
import com.dpbo.telkomedika.gui.pasien.NomorAntreanView;
import com.dpbo.telkomedika.gui.pasien.NotifikasiView;
import com.dpbo.telkomedika.gui.pasien.PanggilAmbulansView;
import com.dpbo.telkomedika.gui.pasien.ProfilView;
import com.dpbo.telkomedika.gui.pasien.RiwayatTemuView;
import com.dpbo.telkomedika.gui.staff.DaftarFeedbackView;
import com.dpbo.telkomedika.gui.staff.DaftarJadwalTemuView;
import com.dpbo.telkomedika.gui.staff.InventarisObatView;
import com.dpbo.telkomedika.gui.staff.KirimNotifikasiView;
import com.dpbo.telkomedika.gui.staff.PanggilAmbulansStaffView;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class HomeView extends BorderPane {
    private User user;
    private VBox menuBox;
    private ScrollPane contentScroll;

    public HomeView(User user) {
        this.user = user;
        setPadding(new Insets(0));

        // Header
        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setSpacing(20);
        header.setStyle("-fx-background-color: #f0f0f0;");

        Label welcome = new Label("Halo, " + user.getNama() + " (" + user.getClass().getSimpleName() + ")");
        welcome.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button logoutBtn = new Button("Logout");
        logoutBtn.setOnAction(e -> MainApp.showLogin());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(welcome, spacer, logoutBtn);

        setTop(header);

        // Sidebar menu
        menuBox = new VBox(10);
        menuBox.setPadding(new Insets(20));
        menuBox.setPrefWidth(220);
        menuBox.setStyle("-fx-background-color: #f0f0f0;");

        setLeft(menuBox);

        // Konten responsif dengan ScrollPane
        contentScroll = new ScrollPane();
        contentScroll.setFitToWidth(true);
        contentScroll.setFitToHeight(true);

        // Tampilkan info awal
        Label infoLabel = new Label("Silakan pilih menu di samping kiri.");
        infoLabel.setStyle("-fx-font-size: 16px;");
        infoLabel.setPadding(new Insets(40, 0, 0, 40));
        contentScroll.setContent(infoLabel);

        setCenter(contentScroll);

        setupMenu();
    }

    private void setupMenu() {
        menuBox.getChildren().clear();

        if (user instanceof com.dpbo.telkomedika.Pasien) {
            Pasien pasien = (Pasien) user;
            addMenu("Buat Jadwal Temu", () -> contentScroll.setContent(new JadwalTemuForm(pasien)));
            addMenu("Lihat Nomor Antrean", () -> contentScroll.setContent(new NomorAntreanView(pasien)));
            addMenu("Lihat Notifikasi", () -> contentScroll.setContent(new NotifikasiView()));
            addMenu("Panggil Ambulans", () -> contentScroll.setContent(new PanggilAmbulansView()));
            addMenu("Beri Feedback", () -> contentScroll.setContent(new FeedbackView()));
            addMenu("Lihat Riwayat Temu", () -> contentScroll.setContent(new RiwayatTemuView(pasien)));
            addMenu("Lihat Profil", () -> contentScroll.setContent(new ProfilView(pasien)));
        } else if (user instanceof com.dpbo.telkomedika.Staff) {
            addMenu("Lihat Daftar Jadwal Temu", () -> contentScroll.setContent(new DaftarJadwalTemuView()));
            addMenu("Lihat Feedback", () -> contentScroll.setContent(new DaftarFeedbackView()));
            addMenu("Kirim Notifikasi ke Pasien", () -> contentScroll.setContent(new KirimNotifikasiView()));
            addMenu("Panggil Ambulans", () -> contentScroll.setContent(new PanggilAmbulansStaffView()));
            addMenu("Lihat Inventaris Obat", () -> contentScroll.setContent(new InventarisObatView()));
        } else if (user instanceof com.dpbo.telkomedika.Dokter) {
            Dokter dokter = (Dokter) user;
            addMenu("Lihat Jadwal Temu", () -> contentScroll.setContent(new JadwalTemuDokterView(dokter)));
        }
    }

    private void addMenu(String menuName, Runnable action) {
        Button btn = new Button(menuName);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setOnAction(e -> action.run());
        menuBox.getChildren().add(btn);
    }
}
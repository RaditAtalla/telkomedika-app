package com.dpbo.telkomedika.gui.staff;

import com.dpbo.telkomedika.App;
import com.dpbo.telkomedika.Obat;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventarisObatView extends VBox {
    private final ObservableList<ObatRow> data = FXCollections.observableArrayList();
    private final TableView<ObatRow> table = new TableView<>();

    public InventarisObatView() {
        setSpacing(15);
        setPadding(new Insets(30, 40, 30, 40));
        setAlignment(javafx.geometry.Pos.TOP_LEFT);

        Label title = new Label("Inventaris Obat");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Separator separator = new Separator();

        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("Belum ada data obat."));

        TableColumn<ObatRow, String> namaCol = new TableColumn<>("Nama Obat");
        namaCol.setCellValueFactory(cellData -> cellData.getValue().namaProperty());

        TableColumn<ObatRow, Integer> stokCol = new TableColumn<>("Stok");
        stokCol.setCellValueFactory(cellData -> cellData.getValue().stokProperty().asObject());

        // Kolom aksi: Tambah, Ambil, Hapus (pop up)
        TableColumn<ObatRow, Void> aksiCol = new TableColumn<>("Aksi");
        aksiCol.setCellFactory(col -> new TableCell<ObatRow, Void>() {
            private final Button tambahBtn = new Button("Tambah");
            private final Button ambilBtn = new Button("Ambil");
            private final Button hapusBtn = new Button("Hapus");
            private final HBox box = new HBox(5, tambahBtn, ambilBtn, hapusBtn);

            {
                tambahBtn.setStyle("-fx-cursor: hand;");
                ambilBtn.setStyle("-fx-cursor: hand;");
                hapusBtn.setStyle("-fx-cursor: hand;");

                tambahBtn.setOnAction(e -> {
                    ObatRow selected = getTableView().getItems().get(getIndex());
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Tambah Stok");
                    dialog.setHeaderText("Tambah stok untuk " + selected.getNama());
                    dialog.setContentText("Jumlah:");
                    dialog.showAndWait().ifPresent(input -> {
                        try {
                            int jumlah = Integer.parseInt(input);
                            if (jumlah <= 0) throw new NumberFormatException();
                            for (Obat o : App.daftarObat) {
                                if (o.getNama().equals(selected.getNama())) {
                                    o.setStok(o.getStok() + jumlah);
                                    refreshTable();
                                    showAlert("Berhasil", "Stok berhasil ditambah.");
                                    break;
                                }
                            }
                        } catch (NumberFormatException ex) {
                            showAlert("Input salah", "Jumlah harus berupa angka lebih dari 0!");
                        }
                    });
                });

                ambilBtn.setOnAction(e -> {
                    ObatRow selected = getTableView().getItems().get(getIndex());
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Ambil Obat");
                    dialog.setHeaderText("Ambil stok dari " + selected.getNama());
                    dialog.setContentText("Jumlah:");
                    dialog.showAndWait().ifPresent(input -> {
                        try {
                            int jumlah = Integer.parseInt(input);
                            if (jumlah <= 0) throw new NumberFormatException();
                            for (Obat o : App.daftarObat) {
                                if (o.getNama().equals(selected.getNama())) {
                                    if (o.getStok() < jumlah) {
                                        showAlert("Stok tidak cukup", "Stok " + o.getNama() + " hanya tersisa " + o.getStok());
                                        return;
                                    }
                                    o.setStok(o.getStok() - jumlah);
                                    refreshTable();
                                    showAlert("Berhasil", "Obat berhasil diambil.");
                                    break;
                                }
                            }
                        } catch (NumberFormatException ex) {
                            showAlert("Input salah", "Jumlah harus berupa angka lebih dari 0!");
                        }
                    });
                });

                hapusBtn.setOnAction(e -> {
                    ObatRow selected = getTableView().getItems().get(getIndex());
                    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Yakin ingin menghapus obat " + selected.getNama() + "?", ButtonType.YES, ButtonType.NO);
                    confirm.setHeaderText("Konfirmasi Hapus");
                    confirm.showAndWait().ifPresent(btn -> {
                        if (btn == ButtonType.YES) {
                            App.daftarObat.removeIf(o -> o.getNama().equals(selected.getNama()));
                            refreshTable();
                            showAlert("Berhasil", "Obat berhasil dihapus.");
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }
        });

        table.getColumns().setAll(namaCol, stokCol, aksiCol);

        refreshTable();

        // Tombol tambah obat baru (pop up, sejajar)
        Button tambahObatBaruBtn = new Button("Tambah Obat Baru");
        Label resultLabel = new Label();

        tambahObatBaruBtn.setOnAction(e -> {
            Dialog<ObatInput> dialog = new Dialog<>();
            dialog.setTitle("Tambah Obat Baru");
            dialog.setHeaderText("Masukkan nama dan stok obat baru");

            // GridPane agar sejajar
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            Label namaLbl = new Label("Nama:");
            TextField namaField = new TextField();
            namaField.setPrefWidth(180);

            Label stokLbl = new Label("Stok:");
            TextField stokField = new TextField();
            stokField.setPrefWidth(180);

            grid.add(namaLbl, 0, 0);
            grid.add(namaField, 1, 0);
            grid.add(stokLbl, 0, 1);
            grid.add(stokField, 1, 1);

            dialog.getDialogPane().setContent(grid);

            ButtonType okButtonType = new ButtonType("Tambah", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == okButtonType) {
                    return new ObatInput(namaField.getText().trim(), stokField.getText().trim());
                }
                return null;
            });

            dialog.showAndWait().ifPresent(input -> {
                String nama = input.nama;
                String stokStr = input.stok;
                if (nama.isEmpty() || stokStr.isEmpty()) {
                    showAlert("Input salah", "Nama dan stok harus diisi!");
                    return;
                }
                int stok;
                try {
                    stok = Integer.parseInt(stokStr);
                } catch (NumberFormatException ex) {
                    showAlert("Input salah", "Stok harus berupa angka!");
                    return;
                }
                if (stok <= 0) {
                    showAlert("Input salah", "Stok harus lebih dari 0!");
                    return;
                }
                for (Obat o : App.daftarObat) {
                    if (o.getNama().equalsIgnoreCase(nama)) {
                        showAlert("Input salah", "Obat sudah ada, gunakan tombol Tambah di tabel.");
                        return;
                    }
                }
                Obat obatBaru = new Obat(nama, stok);
                App.daftarObat.add(obatBaru);
                refreshTable();
                showAlert("Berhasil", "Obat berhasil ditambahkan.");
            });
        });

        getChildren().addAll(
            title, separator,
            table,
            tambahObatBaruBtn,
            resultLabel
        );
        setMaxWidth(Double.MAX_VALUE);
    }

    private void refreshTable() {
        data.clear();
        for (Obat o : App.daftarObat) {
            data.add(new ObatRow(o.getNama(), o.getStok()));
        }
        table.setItems(data);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static class ObatInput {
        String nama;
        String stok;
        ObatInput(String nama, String stok) {
            this.nama = nama;
            this.stok = stok;
        }
    }

    public static class ObatRow {
        private final SimpleStringProperty nama;
        private final SimpleIntegerProperty stok;

        public ObatRow(String nama, int stok) {
            this.nama = new SimpleStringProperty(nama);
            this.stok = new SimpleIntegerProperty(stok);
        }

        public String getNama() { return nama.get(); }
        public SimpleStringProperty namaProperty() { return nama; }
        public SimpleIntegerProperty stokProperty() { return stok; }
    }
}

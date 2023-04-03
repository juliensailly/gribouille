module iut.gon.gribouille_tp1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens iut.gon.gribouille_tp1 to javafx.fxml;
    exports iut.gon.gribouille_tp1;
}

module iut.gon.gribouille_tp1 {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.base;
	requires javafx.graphics;
	requires javafx.base;

    opens iut.gon.gribouille to javafx.fxml;
    exports iut.gon.gribouille;
}

module iut.gon.gribouille {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.base;
	requires javafx.graphics;
	requires javafx.base;

    opens iut.gon.gribouille to javafx.fxml;
    opens controleurs;
    exports iut.gon.gribouille;
}

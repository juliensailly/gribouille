module iut.gon.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens iut.gon.project to javafx.fxml;
    exports iut.gon.project;
}

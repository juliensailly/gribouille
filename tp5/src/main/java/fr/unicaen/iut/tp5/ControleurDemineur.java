package fr.unicaen.iut.tp5;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleurDemineur implements Initializable {
    ModeleDemineur modeleDemineur;
    @FXML
    private RadioMenuItem diffFacile;

    @FXML
    private ToggleGroup difficulte;

    @FXML
    private RadioMenuItem diffMoyen;

    @FXML
    private RadioMenuItem diffDifficile;

    @FXML
    private TextField textfiledMarque;

    @FXML
    private TextField textfielInconnu;

    @FXML
    private GridPane gridpane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modeleDemineur = new ModeleDemineur(10, 10, 10);

        textfiledMarque.textProperty().bind(modeleDemineur.nbMarquesProperty().asString());
        textfielInconnu.textProperty().bind(modeleDemineur.nbInconnuesProperty().asString());

        difficulte.selectedToggleProperty().addListener(obs -> initGrille(difficulte.getSelectedToggle().getUserData().toString()));
    }

    private void initGrille(String userData) {
        gridpane.getColumnConstraints().clear();
        gridpane.getRowConstraints().clear();

        int [] parsedUserData = ModeleDemineur.parseUserData(userData);
        modeleDemineur = new ModeleDemineur(parsedUserData[0], parsedUserData[1], parsedUserData[2]);

        gridpane.getColumnConstraints().add(new ColumnConstraints(32));
        gridpane.getRowConstraints().add(new RowConstraints(32));
    }
}

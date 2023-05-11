package fr.unicaen.iut.tp5;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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

    Background inconnu;
    Background libre;
    Background echec;
    Background marquee;
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

        for (int i = 0; i < parsedUserData[0]; i++) {
            for (int j = 0; j < parsedUserData[1]; j++) {
                gridpane.add(new Label(""), i, j);
            }
        }
        gridpane.getColumnConstraints().add(new ColumnConstraints(32));
        gridpane.getRowConstraints().add(new RowConstraints(32));

        inconnu = new Background(new BackgroundFill(Color.AQUA, new CornerRadii(20), new Insets(0)));
        libre = new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(0), new Insets(0)));
        echec = new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0)));
        marquee = new Background(new BackgroundFill(Color.LEMONCHIFFON, new CornerRadii(0), new Insets(0)));

        for (int i = 0; i < gridpane.getColumnCount(); i++) {
            for (int j = 0; j < gridpane.getRowCount(); j++) {
                Label label = new Label();
                label.setPrefSize(31, 31);
                label.setBackground(inconnu);
                label.setTextAlignment(TextAlignment.CENTER);
                label.textProperty().bind(modeleDemineur.texteProperty(i, j));
                int finalI = i;
                int finalJ = j;
                label.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (event.getButton().equals(MouseButton.PRIMARY)) {
                        modeleDemineur.revele(finalI, finalJ);
                    } else if (event.getButton().equals(MouseButton.SECONDARY)) {
                        modeleDemineur.marque(finalI, finalJ);
                    }

                    if (modeleDemineur.estMarquee(finalI, finalJ)) {
                        label.setBackground(marquee);
                    } else if (modeleDemineur.estRevelee(finalI, finalJ)) {
                        label.setBackground(libre);
                    } else if (modeleDemineur.estPerdu()) {
                        label.setBackground(echec);
                    }
                });
                gridpane.add(label, i, j);
            }
        }
    }
}

package fr.unicaen.iut.tp5;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
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
    private MenuItem quitter;

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
    Stage stage;
    boolean gameLost;

    public final int paneWidth = 32;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialisation d'une partie de difficulté moyenne au lancement
        initGrille(difficulte.getSelectedToggle().getUserData().toString());

        difficulte.selectedToggleProperty().addListener(obs -> {
            initGrille(difficulte.getSelectedToggle().getUserData().toString());
            resizer(difficulte.getSelectedToggle().getUserData().toString(), paneWidth);
        });
        gridpane.getColumnConstraints().add(new ColumnConstraints(paneWidth));
        gridpane.getRowConstraints().add(new RowConstraints(paneWidth));
    }

    private void initGrille(String userData) {
        gridpane.getColumnConstraints().clear();
        gridpane.getRowConstraints().clear();
        gameLost = false;

        boolean bool = false;
        while (!bool) {
            if (gridpane.getChildren().isEmpty()) {
                bool = true;
            } else if (gridpane.getChildren().size() == 1 && gridpane.getChildren().get(0).getClass().equals(javafx.scene.Group.class)) {
                bool = true;
            } else {
                gridpane.getChildren().remove(gridpane.getChildren().size() - 1);
            }
        }

        int[] parsedUserData = ModeleDemineur.parseUserData(userData);
        modeleDemineur = new ModeleDemineur(parsedUserData[0], parsedUserData[1], parsedUserData[2]);

        textfiledMarque.textProperty().bind(modeleDemineur.nbMarquesProperty().asString());
        textfielInconnu.textProperty().bind(modeleDemineur.nbInconnuesProperty().asString());

        inconnu = new Background(new BackgroundFill(Color.AQUA, new CornerRadii(20), new Insets(0)));
        libre = new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(0), new Insets(0)));
        echec = new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0)));
        marquee = new Background(new BackgroundFill(Color.LEMONCHIFFON, new CornerRadii(0), new Insets(0)));

        for (int i = 0; i < parsedUserData[0]; i++) {
            for (int j = 0; j < parsedUserData[1]; j++) {
                Label label = new Label();
                label.setPrefSize(31, 31);
                label.setBackground(inconnu);
                label.setAlignment(Pos.CENTER);
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
                    } else if (modeleDemineur.estRevelee(finalI, finalJ) && Objects.equals(modeleDemineur.getText(finalI, finalJ), "X")) {
                        label.setBackground(echec);
                        modeleDemineur.perdu.set(false); //To allow the player to continue playing
                        gameLost = true; //To register the loss
                    } else if (modeleDemineur.estRevelee(finalI, finalJ) && Objects.equals(modeleDemineur.getText(finalI, finalJ), "0")) {
                        label.setBackground(libre);
                        for (int k = 0; k < gridpane.getChildren().size(); k++) {
                            ObservableList<Node> tab = gridpane.getChildren();
                            if (tab.get(k).getClass().equals(Label.class)) {
                                Label lab = (Label) tab.get(k);
                                if (!lab.getText().equals("?") && !lab.getText().equals("X") && !lab.getText().equals("P")) {
                                    lab.setBackground(libre);
                                }
                            }
                        }
                    } else if (modeleDemineur.estRevelee(finalI, finalJ) && event.getButton().equals(MouseButton.PRIMARY)) {
                        label.setBackground(libre);
                    } else if (modeleDemineur.estRevelee(finalI, finalJ) && event.getButton().equals(MouseButton.SECONDARY) && Objects.equals(modeleDemineur.getText(finalI, finalJ), "?")) {
                        label.setBackground(marquee);
                    }

                    // Check if the game has been won or lost
                    if (gameLost && modeleDemineur.getNbInconnues() == 0) {
                        Dialog<Object> winDialog = new Dialog<>();
                        winDialog.setTitle("You lost...");
                        winDialog.setContentText("At least one mine exploded... You'll do better next time!");
                        winDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        winDialog.showAndWait();
                        initGrille(difficulte.getSelectedToggle().getUserData().toString());
                    } else if (!gameLost && modeleDemineur.getNbInconnues() == modeleDemineur.getNbMines()) {
                        Dialog<Object> winDialog = new Dialog<>();
                        winDialog.setTitle("You won!");
                        winDialog.setContentText("Congratulations, you won!");
                        winDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        winDialog.showAndWait();
                        initGrille(difficulte.getSelectedToggle().getUserData().toString());
                    }
                });
                gridpane.add(label, i, j);
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void resizer(String userData, int width) {
        int[] parsedUserData = ModeleDemineur.parseUserData(userData);

        if (width * parsedUserData[0] + 20 < 660) {
            stage.setWidth(660);
        } else {
            stage.setWidth(width * parsedUserData[0] + 20);
        }
        stage.setHeight(width * parsedUserData[1] + 100);
    }

    public void onQuitterClick() {
        Platform.exit();
    }
}

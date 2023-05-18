package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import iut.gon.gribouille.Dialogues;
import iut.gon.modele.Dessin;
import iut.gon.modele.Etoile;
import iut.gon.modele.Figure;
import iut.gon.modele.Trace;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import outils.OutilCrayon;
import outils.OutilEtoile;
import outils.Outils;

public class Controleur implements Initializable {

    public Figure trace;
    public Etoile etoile;

    public SimpleDoubleProperty prevX;
    public SimpleDoubleProperty prevY;

    public SimpleObjectProperty<Color> couleur;
    public SimpleIntegerProperty epaisseur;
    public SimpleStringProperty outilLabel;

    public @FXML CouleursController couleursController;
    public @FXML DessinController dessinController;
    public @FXML MenusController menusController;
    public @FXML StatutController statutController;

    public int index;

    public Stage stage;
    public final Dessin dessin;

    public Outils outilCrayon;

    public Controleur(Stage stage, Dessin dessin) {
        this.dessin = dessin;

        prevX = new SimpleDoubleProperty();
        prevY = new SimpleDoubleProperty();
        couleur = new SimpleObjectProperty<Color>(Color.BLACK);
        epaisseur = new SimpleIntegerProperty(1);
        this.stage = stage;
        this.outilCrayon = new OutilCrayon(this);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, evt -> onKeyPressed(evt.getText()));
    }


    public void initialize(URL url, ResourceBundle ressourceBundle) {

        couleursController.setControleur(this);
        dessinController.setControleur(this);
        menusController.setControleur(this);
        statutController.setControleur(this);

        dessinController.centralCanva.widthProperty().bind(dessinController.central_pane.widthProperty());
        dessinController.centralCanva.heightProperty().bind(dessinController.central_pane.heightProperty());
        dessinController.centralCanva.widthProperty().addListener(obs -> reDraw());
        dessinController.centralCanva.heightProperty().addListener(obs -> reDraw());
        Bindings.bindBidirectional(statutController.XlabelValue.textProperty(), prevX, new NumberStringConverter());
        Bindings.bindBidirectional(statutController.YlabelValue.textProperty(), prevY, new NumberStringConverter());

        index = -1;
        prevX.set(0);
        prevY.set(0);

        stage.setOnCloseRequest(windowEvent -> {
            if (!Dialogues.confirmation()) {
                windowEvent.consume();
            }
        });

        Bindings.bindBidirectional(statutController.thicknessLabelValue.textProperty(), epaisseur, new NumberStringConverter());
        //statutController.colorLabel.textProperty().bind(couleur.asString());
        outilLabel = new SimpleStringProperty("Outil : Crayon");
        statutController.toolLabel.textProperty().bind(outilLabel);
    }

    /**
     * Fonction permettant de redessinner le canvas lors du redimensionnement de la
     * fenêtre et donc du canvas.
     */
    public void reDraw() {
        // Efface le canva afin d'éviter de superposer les traits.
        dessinController.centralCanva.getGraphicsContext2D().clearRect(0, 0, dessinController.centralCanva.getWidth(),
                dessinController.centralCanva.getHeight());

        for (Figure trace : dessin.getFigures()) {
            prevX.set(trace.getPoints().get(0).getX());
            prevY.set(trace.getPoints().get(0).getY());


            for (int i = 1; i < trace.getPoints().size(); i++) {
                dessinController.centralCanva.getGraphicsContext2D().setLineWidth(trace.getEpaisseur());
                dessinController.centralCanva.getGraphicsContext2D().setStroke(Color.valueOf(trace.getCouleur()));
                if (trace instanceof Trace) {
                    dessinController.centralCanva.getGraphicsContext2D().strokeLine(prevX.get(), prevY.get(),
                            trace.getPoints().get(i).getX(), trace.getPoints().get(i).getY());
                    prevX.set(trace.getPoints().get(i).getX());
                    prevY.set(trace.getPoints().get(i).getY());
                } else if (trace instanceof Etoile) {
                    double cX = ((Etoile) trace).getCentre().getX();
                    double cY = ((Etoile) trace).getCentre().getY();
                    dessinController.centralCanva.getGraphicsContext2D().strokeLine(trace.getPoints().get(i).getX(), trace.getPoints().get(i).getY(),
                            cX, cY);
                }
            }

        }
    }

    /**
     * Fonction permettant d'afficher les coordonnées de la souris dans les Labels
     * situés sous le canvas.
     *
     * @param evt Utilisé pour récupérer les coordonnées de la souris.
     */
    public void onMouseMoved(MouseEvent evt) {
        // On teste la longueur des coordonnées pour éviter les problèmes d'affichage
        statutController.XlabelValue.textProperty()
                .set(Double.toString(evt.getX()).length() > 5 ? Double.toString(evt.getX()).substring(0, 5)
                        : Double.toString(evt.getX()));
        statutController.YlabelValue.textProperty()
                .set(Double.toString(evt.getY()).length() > 5 ? Double.toString(evt.getY()).substring(0, 5)
                        : Double.toString(evt.getY()));
    }

    /**
     * Fonction permettant de fermer la fenêtre depuis le menu.
     */
    public void onQuitter() {
        if (Dialogues.confirmation()) {
            Platform.exit();
        }
    }

    public void onCrayon() {
        outilLabel.set("Outil : Crayon");
        outilCrayon = new OutilCrayon(this);
    }

    public void onEtoile() {
        outilLabel.set("Outil : Etoile");
        outilCrayon = new OutilEtoile(this);
    }

    public void setEpaisseur(String value) {
        this.epaisseur = new SimpleIntegerProperty(Integer.parseInt(value));
        dessinController.setEpaisseur(Integer.parseInt(value));
        statutController.setThicknessLabelValue(value);
    }

    public void setCouleur(Paint fill) {
        this.couleur = new SimpleObjectProperty<Color>((Color) fill);
        dessinController.setCouleur(fill);
    }

    private void onKeyPressed(String eventTxt) {
        try {
            Integer.parseInt(eventTxt);
            setEpaisseur(eventTxt);
        } catch(NumberFormatException exception) {
            switch (eventTxt) {
                case "c":
                    onCrayon();
                    break;
                case "e":
                    onEtoile();
                    break;
                case "r":
                    setCouleur(new Color(1, 0, 0, 1));
                    break;
                case "v":
                    setCouleur(new Color(0, 1, 0, 1));
                    break;
                case "b":
                    setCouleur(new Color(0, 0, 1, 1));
                    break;
                case "t":
                    setCouleur(new Color(0, 1, 1, 1));
                    break;
                case "m":
                    setCouleur(new Color(1, 0, 1, 1));
                    break;
                case "j":
                    setCouleur(new Color(1, 1, 0, 1));
                    break;
                case "n":
                    setCouleur(new Color(0, 0, 0, 1));
                    break;
                case "w":
                    setCouleur(new Color(1, 1, 1, 1));
                    break;

                default:
                    onCrayon();
                    setEpaisseur("1");
                    setCouleur(new Color(255,255,255,1));
            }
        }
    }
}

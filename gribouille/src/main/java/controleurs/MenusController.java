package controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class MenusController implements Initializable {
    @FXML
    public ToggleGroup epaisseur_group;
    @FXML
    public ToggleGroup outilGroupe;
    @FXML
    public RadioMenuItem crayon;
    @FXML
    public RadioMenuItem etoile;

    Controleur controleur;

    public void setControleur(Controleur c) {
        this.controleur = c;
    }

    @FXML
    public void onQuitte() {
        controleur.onQuitter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        crayon.setSelected(true);
        outilGroupe.selectedToggleProperty().addListener(obs -> {
            if (crayon.isSelected()) {
                controleur.onCrayon();
            } else if (etoile.isSelected()) {
                controleur.onEtoile();
            }
        });

        epaisseur_group.selectedToggleProperty().addListener(obs -> {
            RadioMenuItem rmi = (RadioMenuItem) epaisseur_group.getSelectedToggle();
            controleur.setEpaisseur(rmi.getText());
        });
    }

    public void onAideClick() {
        Dialog<Object> helpDialog = new Dialog<>();
        helpDialog.setTitle("Aide");
        helpDialog.setHeaderText("Gribouille - Aide :");
        helpDialog.setContentText("Bonjour et bienvenue dans le logiciel de dessin Gribouille réalisé par Julien Sailly dans le cadre" +
                " de la ressource JavaFX R2.02 du BUT Informatique de l'IUT de Caen.\n\n" +
                "Les raccourcis claviers disponibles sont :\n" +
                "Touche \"c\" : Outil Crayon\n" +
                "Touche \"e\" : Outil Etoile\n" +
                "Les touches du pavé numérique permettent de modifier l'épaisseur du trait.\n" +
                "Touche \"r\" : Couleur rouge\n" +
                "Touche \"v\" : Couleur verte\n" +
                "Touche \"b\" : Couleur bleue\n" +
                "Touche \"t\" : Couleur cyan (turquoise)\n" +
                "Touche \"m\" : Couleur magenta\n" +
                "Touche \"j\" : Couleur jaune\n" +
                "Touche \"n\" : Couleur noire\n" +
                "Touche \"w\" : Couleur blanche\n");
        helpDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        helpDialog.setWidth(500);
        helpDialog.setHeight(600);
        helpDialog.showAndWait();
    }

    public void onSauvegardeClick() {
        controleur.sauvegarde();
    }

    public void onChargerClick() { controleur.charge(); }
}

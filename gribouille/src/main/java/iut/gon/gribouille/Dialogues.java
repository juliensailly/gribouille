package iut.gon.gribouille;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author Julien Sailly
 */
public class Dialogues {
    static Alert confirmation = new Alert(AlertType.CONFIRMATION);

    /**
     * Fonction permettant d'afficher la boîte de dialogue en cas de fermeture de la fenêtre.
     *
     * @return La décision de l'utilisateur.
     */
    public static boolean exitFileModified() {
        confirmation.setContentText("Voulez vous sauvegarder votre dessin avant de quitter l'application Gribouille ?");
        confirmation.setHeaderText("Sauvegarde du dessin");
        Optional<ButtonType> choice = confirmation.showAndWait();
        return choice.map(buttonType -> buttonType.equals(ButtonType.OK)).orElse(true);
    }
}

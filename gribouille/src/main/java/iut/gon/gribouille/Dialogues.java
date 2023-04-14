package iut.gon.gribouille;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * 
 * @author Julien Sailly
 *
 */
public class Dialogues {
	static Alert confirmation = new Alert(AlertType.CONFIRMATION);
	
	/**
	 * Fonction permettant d'afficher la boîte de dialogue en cas de fermeture de la fenêtre.
	 * 
	 * @return La décision de l'utilisateur.
	 */
	public static boolean confirmation() {
		confirmation.setContentText("Do you really want to exit ?");
		Optional<ButtonType> choice = confirmation.showAndWait();
		return choice.get().equals(ButtonType.OK);
	}
	
	/**
	 * Permet de consumer l'évènement de fermeture de la fenêtre en cas d'annulation de l'action
	 * par l'utilisateur.
	 * 
	 * @param stage Le Stage de l'application que l'utilisateur essaye de fermer.
	 */
	public static void prepareFermeture(@SuppressWarnings("exports") Stage stage) {
		stage.setOnCloseRequest(event -> {
			if (!confirmation()) {
                    event.consume();
            }
    });
	}
}

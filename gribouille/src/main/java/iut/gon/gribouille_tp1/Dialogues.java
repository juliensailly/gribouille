package iut.gon.gribouille_tp1;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Dialogues {
	static Alert confirmation = new Alert(AlertType.CONFIRMATION);
	
	public static boolean confirmation() {
		confirmation.setContentText("Do you really want to exit ?");
		Optional<ButtonType> choice = confirmation.showAndWait();
		return choice.get().equals(ButtonType.OK);
	}
	

	public static void prepareFermeture(Stage stage) {
		stage.setOnCloseRequest(event -> {
			if (!confirmation()) {
                    event.consume();
            }
    });
	}
}

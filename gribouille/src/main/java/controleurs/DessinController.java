package controleurs;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class DessinController {
	@FXML
	public Canvas centralCanva;
	@FXML
	public Pane central_pane;
	@FXML
	Controleur controleur;

	public void setControleur(Controleur c) {
		this.controleur = c;
	}
}

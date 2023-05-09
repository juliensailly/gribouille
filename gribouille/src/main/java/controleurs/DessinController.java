package controleurs;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DessinController {
	@FXML
	public Canvas centralCanva;
	@FXML
	public Pane central_pane;
	
	private Controleur controleur;
	

	public void setControleur(Controleur c) {
		this.controleur = c;
	}
	
	@FXML
	public void onMousePressed(MouseEvent e) {
		controleur.onMousePressed(e);
	}
	
	@FXML
	public void onMouseMoved(MouseEvent e) {
		controleur.onMouseMoved(e);
	}
	
	@FXML
	public void onMouseDragged(MouseEvent e) {
		controleur.onMouseDragged(e);
	}
}

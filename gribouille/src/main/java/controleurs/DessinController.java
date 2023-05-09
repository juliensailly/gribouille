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
	
	public void onMousePressed(MouseEvent e) {
		controleur.onMousePressed(e);
	}
	
	public void onMouseMoved(MouseEvent e) {
		controleur.onMouseMoved(e);
	}
	
	public void onMouseDragged(MouseEvent e) {
		controleur.onMouseDragged(e);
	}
}

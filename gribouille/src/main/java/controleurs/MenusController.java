package controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class MenusController {
	@FXML
	public ToggleGroup epaisseur_group;
	@FXML
	public ToggleGroup outil_group;
	
	Controleur controleur;

	public void setControleur(Controleur c) {
		this.controleur = c;
	}
}

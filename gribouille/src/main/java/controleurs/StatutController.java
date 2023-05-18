package controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatutController {
	@FXML
	public Label XlabelValue;
	@FXML
	public Label YlabelValue;
	@FXML
	public Label colorLabel;
	@FXML
	public Label thicknessLabelValue;
	@FXML
	public Label toolLabel;
	
	Controleur controleur;

	public void setControleur(Controleur c) {
		this.controleur = c;
	}

	public void setThicknessLabelValue(String epaisseur) {
		thicknessLabelValue.setText(epaisseur);
	}
}

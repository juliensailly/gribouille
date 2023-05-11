package controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

public class CouleursController {
	@FXML
	public Rectangle black;
	@FXML
	public Rectangle blue;
	@FXML
	public Rectangle cyan;
	@FXML
	public Rectangle green;
	@FXML
	public Rectangle magenta;
	@FXML
	public Rectangle red;
	@FXML
	public Rectangle white;
	@FXML
	public Rectangle yellow;
	@FXML
	public ColorPicker sideColorPicker;

	Controleur controleur;

	public void setControleur(Controleur c) {
		this.controleur = c;
	}
}

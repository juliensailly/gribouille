package controleurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class CouleursController implements Initializable {
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
	@FXML
	public VBox colorVBox;

	Controleur controleur;
	Rectangle precedent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colorVBox.addEventHandler(MouseEvent.MOUSE_CLICKED, evt -> {
			if (evt.getTarget().getClass().equals(Rectangle.class)) {
				Rectangle rect = (Rectangle) evt.getTarget();
				controleur.setCouleur(rect.getFill());
				rect.setArcHeight(10);
				rect.setArcWidth(10);
				rect.setStrokeWidth(5);
				rect.setHeight(rect.getHeight() - 3);
				rect.setWidth(rect.getWidth() - 3);
				if (precedent != null) {
					precedent.setArcWidth(5);
					precedent.setArcHeight(5);
					precedent.setStrokeWidth(1);
					precedent.setHeight(50);
					precedent.setWidth(50);
				}
				precedent = rect;
			}
		});
	}

	public void setControleur(Controleur c) {
		this.controleur = c;
	}


}
